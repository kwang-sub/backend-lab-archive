from getpass import _raw_input

number = 23
running = True

while running:
    guess = int(_raw_input('숫자 : '))
    if guess == number:
        print("같음")
        running = False
    elif guess < number:
        print("입력값 작음")
    else:
        print("입력값 큼")
else:
    print("반복문 종료")    

print("종료")
