import random

def generate_tc_kimlik_numbers(n):
    def generate_single_tc():
        # İlk 9 basamağı rastgele oluştur (1-9 ile başlar, ardından 0-9 arasında devam eder).
        first_9 = [random.randint(1, 9)] + [random.randint(0, 9) for _ in range(8)]
        
        # 10. basamağı hesapla:
        odd_sum = sum(first_9[0:9:2])  # 1, 3, 5, 7, 9. basamaklar
        even_sum = sum(first_9[1:8:2])  # 2, 4, 6, 8. basamaklar
        tenth_digit = ((7 * odd_sum) - even_sum) % 10
        first_9.append(tenth_digit)
        
        # 11. basamağı hesapla:
        total_sum = sum(first_9)
        eleventh_digit = total_sum % 10
        first_9.append(eleventh_digit)
        
        # Listeyi birleştir ve string olarak döndür.
        return ''.join(map(str, first_9))
    
    # n adet TC Kimlik Numarası oluştur.
    return [generate_single_tc() for _ in range(n)]

def save_tc_numbers_to_file(tc_numbers, filename):
    with open(filename, 'w') as file:
        for tc in tc_numbers:
            file.write(tc + '\n')

# Kullanım
n = 100000  # 5 adet TC Kimlik Numarası oluştur
tc_numbers = generate_tc_kimlik_numbers(n)
save_tc_numbers_to_file(tc_numbers, 'tc_kimlik_numeralari.txt')

print(f"{n} adet TC Kimlik Numarası 'tc_kimlik_numeralari.txt' dosyasına kaydedildi.")
