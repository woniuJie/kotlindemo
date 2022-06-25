# coding=utf-8
# This is a sample Python script.

# Press ⌃R to execute it or replace it with your code.
# Press Double ⇧ to search everywhere for classes, files, tool windows, actions, and settings.
import os
import re

result_list = []
pattern = re.compile('@color/color_([A-Fa-f0-9]{6})')   #定义正则表达式
pattern_content = re.compile('(?<=(@color/color_))([A-Fa-f0-9]{6})')
fs = os.listdir('/users/shijiezhang/downloads/testfile')
for i in range(len(fs)):    #构造文件路径
    fs[i] = '/users/shijiezhang/downloads/testfile' + '/' + fs[i]

for i in range(len(fs)):
    f = open(fs[i])    #打开文件
    t = f.read()       #读取文件
    f.close()          #关闭文件

    result_temp = pattern.findall(t)  #进行匹配
    print(result_temp)
    for j in range(len(result_temp)):
        temp_wait_to_replace = pattern_content.findall(result_temp[j])
        print(temp_wait_to_replace)
        result_temp[j] = temp_wait_to_replace[0]   #完成最终内容的提取

    result_list = result_list+result_temp   #将两个列表合起来，result_list每次都新增这次新找到的

    print(fs[i],' is finished')    #提示一下这个文件已完成

result_list_to_write = '\n'.join(result_list)   #用换行符拼接成整一个字符串，方便写入文件

print(result_list_to_write)

f = open('/users/shijiezhang/downloads/testfile/test','w')
f.write(result_list_to_write)
f.close()             #写入文件完成

print('result file is ok')   #又是提示一下，完工啦，啦啦啦啦啦

