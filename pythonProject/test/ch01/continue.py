from getpass import _raw_input

while True:
    s = _raw_input("Enter something: ")
    if s == 'quit':
        break
    if len(s) < 3:
        print("Too small")
        continue
    print("Input is of sufficient length")
