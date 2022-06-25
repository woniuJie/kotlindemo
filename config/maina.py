# coding=utf-8
# This is a sample Python script.

# Press ⌃R to execute it or replace it with your code.
# Press Double ⇧ to search everywhere for classes, files, tool windows, actions, and settings.
import os
import re

if __name__ == '__main__':
   print '=========success========='
#    f = open('/users/shijiezhang/downloads/testfile/info','w')
#    f.write('zhangshijie')
#    f.close()
#
#    fs = os.listdir('/users/shijiezhang/downloads/testfile')
#    print(fs)
#
#    for i in range(len(fs)):
#         fs[i] = 'files'+'/'+fs[i]
#
#    print(fs)

   tt = 'zhangshijie'
   pattern = re.compile('zhang.hi')
   result = pattern.findall(tt)
   print(result)


   pattern_re = re.compile('zhang')
   for i in range(len(result)):
       temp = pattern_re.findall(result[i])
       result[i] = temp[0]


   print(result)

   f = open('/users/shijiezhang/downloads/testfile/info','w')
   result_wait = '\n'.join(result)
   f.write(result_wait)
   f.close()

