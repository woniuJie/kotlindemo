# -*- coding: UTF-8 -*-
import os
import re

reg_xml = re.compile("\"@color/col(or)?_([0-9a-fA-F]{8}|[0-9a-fA-F]{6}|[0-9a-fA-F]{3})\"")


def get_sub_str(color_str):
    group_str = color_str.group(0)
    if group_str == "":
        return ""
    print(group_str.replace("@color/color_", "#").replace("@color/col_", "#"))
    return group_str.replace("@color/color_", "#").replace("@color/col_", "#")


def replace_xml_color(file_path):
    ends = file_path.endswith(".xml")
    if not ends:
        print(file_path)
        pass
    else:
        f = open(file_path, "r+")
        lines = f.readlines()
        data = ''
        for lineStr in lines:
            # print(lineStr)
            repl = reg_xml.sub(get_sub_str, lineStr)
            # print(repl)
            data += repl

        f.close()
        output = open(file_path, "w")
        output.writelines(data)
        output.close()

def findAllFile(base):
    for root,ds,fs in os.walk(base):
        for f in fs:
            filename = os.path.join(root, f)
            replace_xml_color(filename)

def main():
    base = '/users/shijiezhang/AndroidStudioProjects/kotlindemo/app/src/main/res/layout1'
    findAllFile(base)

if __name__ == '__main__':
    main()
