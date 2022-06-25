# coding=utf-8
# This is a sample Python script.

# Press ⌃R to execute it or replace it with your code.
# Press Double ⇧ to search everywhere for classes, files, tool windows, actions, and settings.
import os
import re

print('xxxxx')
f = open('/users/shijiezhang/downloads/testfile/info','r')
alllines = f.readlines()
f.close()
f = open('/users/shijiezhang/downloads/testfile/info','w')
for eachline in alllines:

    pattern = re.compile('(@color/color_[A-Fa-f0-9]{6})')
    pattern_only = re.compile('@color/color_([A-Fa-f0-9]{6})')
    result = pattern.findall(eachline)
    result_only = pattern_only.findall(eachline)

    if len(result):
            print('开始------')
            print(result)
            print('哈哈哈哈------')
            print(result[0])
            print(result_only[0])
            print('结束------')

            one = result[0]
            two = result_only[0]

            a = re.sub(one,two,eachline)
            f.writelines(a)
            print('eachline kong')
    else:
          print "asdf line",eachline
          f.writelines(eachline)
f.close()



# pattern = re.compile('(@color/(color|col)_)([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3}|[A-Fa-f0-9]{8})')   #定义正则表达式
# pattern_content = re.compile('(?<=(@color/(color|col)_))([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3}|[A-Fa-f0-9]{8})')
# fs = os.listdir('/users/shijiezhang/downloads/testfile')
# for i in range(len(fs)):    #构造文件路径
#     fs[i] = '/users/shijiezhang/downloads/testfile' + '/' + fs[i]
#
# for i in range(len(fs)):
#     f = open(fs[i])    #打开文件
#     t = f.read()       #读取文件
#     f.close()          #关闭文件
#
#     result_temp = pattern.findall(t)  #进行匹配
#     print(result_temp)
#     for j in range(len(result_temp)):
#         temp_wait_to_replace = pattern_content.findall(result_temp[j])
#         print(temp_wait_to_replace)
