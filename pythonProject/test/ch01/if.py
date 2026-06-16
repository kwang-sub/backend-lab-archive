from getpass import _raw_input

number = 23
guess = int(_raw_input('Enter an integer : '))

if guess == number:
    print("congratulations you guessed it")
elif guess < number:
    print("No, it is a little higher than that")
else:
    print("No it is a little lower than that")
    
print("done")