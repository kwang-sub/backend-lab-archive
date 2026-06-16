class SchoolMember:
    '''Represents any school member'''

    def __init__(self, name, age):
        self.name = name
        self.age = age
        print("생성 ",self.name)

    def tell(self):
        print("name {} age {}".format(self.name, self.age))
class Teacher(SchoolMember):
    def __init__(self, name, age, salary):
        SchoolMember.__init__(self, name, age)
        self.salary = salary
        print("교사 생성", self.name)

    def tell(self):
        print("salary {:d}".format(self.salary))

class Student(SchoolMember):
    '''Represents a student.'''
    def __init__(self, name, age, marks):
        SchoolMember.__init__(self, name, age)
        self.marks = marks
        print('(Initialized Student: {})'.format(self.name))

    def tell(self):
        SchoolMember.tell(self)
        print('Marks: "{:d}"'.format(self.marks))

t = Teacher("Mrs. Shrividya", 40, 30000)
s = Student('Swaroop', 25, 75)

members = [t, s]
for member in members:
    member.tell()