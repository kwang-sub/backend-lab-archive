from builtins import print

zoo = ('python', 'elephant', 'penguin')
print('Number of animals in the zoo is ', len(zoo))

new_zoo = 'monkey', 'camel', zoo
print(len(new_zoo))
print(new_zoo)
print(len(new_zoo[1]))