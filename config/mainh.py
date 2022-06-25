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

        patternpre = '(@color/color_[A-Fa-f0-9]{6})'
        resultpre = re.findall(patternpre,eachline,re.IGNORECASE)

        patternafter = '@color/color_([A-Fa-f0-9]{6})'
        resultafter = re.findall(patternafter,eachline,re.IGNORECASE)

        if len(resultpre):
                print(resultpre)
                print(resultafter)

                pre = resultpre[0]
                after = "#"+resultafter[0]
                print("修改了这个文件名-- "+fileName+" --的这个名-- "+pre+" --改成了这个--- "+after)

                a = re.sub(pre,after,eachline)
                f.writelines(a)
        else:
              f.writelines(eachline)
    f.close()

def findAllFile(base):
    for root,ds,fs in os.walk(base):
        for f in fs:
            filename = os.path.join(root, f)
            relpaceCorlr(filename)

def main():
    base = '/users/shijiezhang/AndroidStudioProjects/kotlindemo/app/src/main/res/layout1'
    findAllFile(base)

if __name__ == '__main__':
    main()

