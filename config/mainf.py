# coding=utf-8
# This is a sample Python script.

# Press ⌃R to execute it or replace it with your code.
# Press Double ⇧ to search everywhere for classes, files, tool windows, actions, and settings.
import os
import re


def relpaceCorlr(fileName):
    f = open(fileName,'r')
    alllines = f.readlines()
    f.close()
    f = open(fileName,'w')
    for eachline in alllines:

        pattern = re.compile('(@color/color_[A-Fa-f0-9]{6})')
        pattern_only = re.compile('@color/color_([A-Fa-f0-9]{6})')
        result = pattern.findall(eachline)
        result_only = pattern_only.findall(eachline)

        if len(result):
                one = result[0]
                two = "#"+result_only[0]
                print("修改了这个文件名-- "+fileName+" --的这个名-- "+one+" --改成了这个--- "+two)

                a = re.sub(one,two,eachline)
                f.writelines(a)
        else:
              f.writelines(eachline)
    f.close()

def findAllFile(base):
    for root,ds,fs in os.walk(base):
        for f in fs:
            yield f

def main():
    base = '/users/shijiezhang/AndroidStudioProjects/kotlindemo/app/src/main/res/layout'
    for i in findAllFile(base):
        print(base+"/"+i)
        relpaceCorlr(base+"/"+i)

if __name__ == '__main__':
    main()

