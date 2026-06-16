import pandas as pd
import numpy as np

# df = pd.DataFrame(np.random.randn(8, 4), columns=['A', 'B', 'C', 'D'])
# s = df.iloc[3]
#
# print(df._append(s, ignore_index=True))

df = pd.DataFrame({'A': ['foo', 'bar', 'foo', 'bar',
                         'foo', 'bar', 'foo', 'foo'],
                   'B': ['one', 'one', 'two', 'three',
                         'two', 'two', 'one', 'three'],
                   'C': np.random.randn(8),
                   'D': np.random.randn(8)})


print(df.groupby(['A','B']).sum())