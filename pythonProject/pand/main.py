import pandas as pd
import numpy as np
import matplotlib.pyplot as plt


s = pd.Series([1, 3, 5, np.nan, 6, 8])
# print(s)

dates = pd.date_range('20130101', periods=6)
# print("===================")
# print(dates)
# print("===================")
df = pd.DataFrame(np.random.rand(6, 4), index=dates, columns=list('ABCD'))
# print(df)
# print("===================")
df2 = pd.DataFrame({'A': 1.,
                    'B': pd.Timestamp('20130102'),
                    'C': pd.Series(1, index=list(range(4)), dtype='float32'),
                    'D': np.array([3]*4, dtype='int32'),
                    'E': pd.Categorical(['test', 'train', 'test', 'train']),
                    'F': 'foo'})
#
# print(df2)
#
# print(df2.sort_index(axis=1, ascending=False))
# print(df2.sort_values(by='E'))

# print(df['20130101':'20130102'])
# print(df2[0:2])

# print(dates[0])
# print(df)
# print(df.loc[dates[0]])
# print(df.loc['20130101':'20130103', ['A','B']])

# print(df[df.A > 0.2])
# print(df[df > 0.5])
# df['E'] = ['one', 'one', 'two', 'three', 'four', 'three']
# print(df)
# print(df[df['E'].isin(['one','three'])])

# s1 = pd.Series([1, 2, 3, 4, 5, 6], index=pd.date_range('20130102', periods=6))
# df['F'] = s1
# df.at[dates[0], 'A'] = 0
# print(df)
# df.iat[0,1] = 10
# print(df)
#
# df.loc[:, 'D'] = np.array([5] * len(df))
# print(df)
#
# df3 = df.reindex(index=dates[0:4], columns=list(df.columns) + ['E'])
# df3.loc[dates[0]:dates[1],'E'] = 1
# print(df3)
# print(df3.fillna(value='?'))

# s = pd.Series([1, 3, 5, np.nan, 6, 8], index=dates).shift(2)
# print(s)
# print(df.sub(s, axis='index'))
# print(df)
# print(df.apply(np.cumsum))
# print(df.apply(lambda x: x.max() - x.min()))

# s = pd.Series(np.random.randint(0, 7, size=10))
# print(s)
# print(s.value_counts())

# s = pd.Series(['A', 'B', 'C', 'AaBb', 'Baca', np.nan, 'CABA', 'dog', 'cat'])
# print(s.str.lower())

df4 = pd.DataFrame(np.random.rand(10, 4))
print("=======")
pieces = [df[:3], df[3:5], df[5:]]
print(pieces[2])
print(pd.concat(pieces))