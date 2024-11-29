import mysql.connector
from tkinter import *
from tkinter import messagebox

# Veritabanı bağlantısı
def connect_to_database():
    return mysql.connector.connect(
        host="localhost",
        user="root",  # MySQL kullanıcı adınızı yazın
        password="",  # MySQL şifrenizi yazın
        database="BankApp"
    )

# Giriş kontrolü
def login():
    tckn = entry_tckn.get()
    password = entry_password.get()

    if not tckn or not password:
        messagebox.showerror("Hata", "Lütfen tüm alanları doldurun!")
        return

    try:
        conn = connect_to_database()
        cursor = conn.cursor()
        cursor.execute("SELECT * FROM users WHERE tckn = %s AND password = %s", (tckn, password))
        user = cursor.fetchone()
        conn.close()

        if user:
            messagebox.showinfo("Başarılı", "Giriş başarılı!")
            show_main_screen()
        else:
            messagebox.showerror("Hata", "TCKN veya şifre yanlış!")
    except mysql.connector.Error as err:
        messagebox.showerror("Veritabanı Hatası", f"Hata: {err}")

# Ana ekran
def show_main_screen():
    login_window.destroy()
    main_window = Tk()
    main_window.title("Bankacılık Uygulaması - Ana Ekran")
    main_window.geometry("400x300")

    Label(main_window, text="Hoşgeldiniz!", font=("Arial", 16)).pack(pady=20)
    Button(main_window, text="Çıkış Yap", command=main_window.destroy).pack(pady=20)

    main_window.mainloop()

# Giriş ekranı
login_window = Tk()
login_window.title("Bankacılık Uygulaması - Giriş")
login_window.geometry("400x300")

Label(login_window, text="TCKN", font=("Arial", 12)).pack(pady=10)
entry_tckn = Entry(login_window, font=("Arial", 12))
entry_tckn.pack()

Label(login_window, text="Şifre", font=("Arial", 12)).pack(pady=10)
entry_password = Entry(login_window, font=("Arial", 12), show="*")
entry_password.pack()

Button(login_window, text="Giriş Yap", font=("Arial", 12), command=login).pack(pady=20)

login_window.mainloop()
