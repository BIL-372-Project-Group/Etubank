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
    def add_account(self, customer_id, iban, account_number, account_status_id, account_type_id, date_opened, balance, currency, bank_id):
        """Yeni bir hesap ekler."""
        try:
            connection = self.connect_to_database()  # Bağlantıyı kur
            cursor = connection.cursor()
            
            # Hesap ekleme SQL komutu
            query = """INSERT INTO account (customer_id, iban, account_number, account_status_id, account_type_id, date_opened, balance, currency, bank_id)
                       VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)"""
            cursor.execute(query, (customer_id, iban, account_number, account_status_id, account_type_id, date_opened, balance, currency, bank_id))
            connection.commit()  # Değişiklikleri kaydet

            print("Hesap başarıyla eklendi.")
        except mysql.connector.Error as err:
            print(f"Hata: {err}")
        finally:
            # Bağlantıyı kapat
            cursor.close()
            connection.close()

    def delete_account(self, account_id):
        """Bir hesabı siler."""
        try:
            connection = self.connect_to_database()  # Bağlantıyı kur
            cursor = connection.cursor()
            
            # Hesap silme SQL komutu
            query = """DELETE FROM account WHERE account_id = %s"""
            cursor.execute(query, (account_id,))
            connection.commit()  # Değişiklikleri kaydet
            
            print("Hesap başarıyla silindi.")
        except mysql.connector.Error as err:
            print(f"Hata: {err}")
        finally:
            # Bağlantıyı kapat
            cursor.close()
            connection.close()

    def add_account_button_click(self):
        """GUI'den hesap eklemek için buton tıklama fonksiyonu."""
        customer_id = 1
        iban = "TR123456789012345678901234"
        account_number = "987654321"
        account_status_id = 1
        account_type_id = 2
        date_opened = "2024-11-29"
        balance = 1000.0
        currency = "USD"
        bank_id = 1
        
        # Veritabanına hesap ekle
        self.add_account(customer_id, iban, account_number, account_status_id, account_type_id, date_opened, balance, currency, bank_id)
        messagebox.showinfo("Başarılı", "Hesap başarıyla eklendi!")

    def delete_account_button_click(self):
        """GUI'den hesap silmek için buton tıklama fonksiyonu."""
        account_id = 1  # Silinecek hesap ID'si
        self.delete_account(account_id)
        messagebox.showinfo("Başarılı", "Hesap başarıyla silindi!")


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


    def create_gui(self):
        """Tkinter GUI'sini oluşturur."""
        root = tk.Tk()
        root.title("Ana Ekran")

        # Hesap Ekle butonu
        add_account_button = tk.Button(root, text="Hesap Ekle", command=self.add_account_button_click)
        add_account_button.pack(pady=10)

        # Hesap Sil butonu
        delete_account_button = tk.Button(root, text="Hesap Sil", command=self.delete_account_button_click)
        delete_account_button.pack(pady=10)

        # Tkinter penceresini başlat
        root.mainloop()

# Uygulamayı başlat
if __name__ == "__main__":
    manager = BankAccountManager()
    manager.create_gui()

"""
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
"""
