listone = [2, 3, 4]
listtwo = [2 * i for i in listone if i % 2 == 0]
print(listtwo)


def powersum(power, *args):
    '''Return the sum of each argument raised to the specified power.'''
    total = 0
    for i in args:
        total += pow(i, power)
    return total

print(powersum(2, 3, 4))

mylist = ['item']
assert len(mylist) >= 1
print(mylist.pop())
