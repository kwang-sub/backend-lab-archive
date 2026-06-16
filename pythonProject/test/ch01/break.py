from getpass import _raw_input

while True:
    s = _raw_input('Enter something: ')
    if s == 'quit':
        break

    print("Length of the string is ", len(s))
print("Done")
