shoplist = ['apple', 'mango', 'carrot', 'banana']

print('I have ', len(shoplist), ' items to purchase')

print('These items are: ')
for item in shoplist:
    print(item)

print("I also have to buy rice")
shoplist.append('rice')
print('rice append ', shoplist)

shoplist.sort(reverse=True)
print('sort ', shoplist)
