shoplist = ['apple', 'mango', 'carrot', 'banana']
name = 'swaroop'

print(shoplist[1::3])

bri = set(['brazil', 'russia', 'india'])
print('india' in bri)
print('usa' in bri)

bric = bri.copy()
bric.add("china")

print(bric.issuperset(bri))

bri.remove('russia')
print(bric & bri)