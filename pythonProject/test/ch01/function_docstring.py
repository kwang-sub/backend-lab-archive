def print_max(x, y):
    '''Prints the maximum of two numbers.

    The two values must be integers.
    '''

    x = int(x)
    y = int(y)

    if x > y:
        print(x)
    else:
        print(y)

print_max(3, 5)
print(print_max.__doc__)