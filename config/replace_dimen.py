# -*- coding: UTF-8 -*-
import os
import re

reg_dimen_xml = re.compile("\"@dimen/(d|s)p?_[0-9]+\"")

def get_sub_dimen_str(dimen_str):
    group_str = dimen_str.group(0)
    if group_str == "":
        return ""
    # print(group_str.replace("@color/color_", "#").replace("@color/col_", "#"))
    print(group_str)
    if group_str == "\"@dimen/dp_05\"":
        return "\"0.5dp\""
    type = "sp"
    if group_str.startswith("\"@dimen/d"):
        type = "dp"

    result = group_str.replace("@dimen/dp_", "").replace("@dimen/sp_", "").replace("@dimen/d_", "").replace("@dimen/s_", "")

    return result[:-1]+type+result[-1]


def replace_dimen(file_path):
    ends = file_path.endswith(".xml")
    if not ends:
#         print(file_path)
        pass
    else:
        f = open(file_path, "r")
        lines = f.readlines()
        data = ''
        for lineStr in lines:
            # print(lineStr)
            repl = reg_dimen_xml.sub(get_sub_dimen_str, lineStr)
            # print(repl)
            data += repl
        f.close()
        output = open(file_path, "w")
        output.writelines(data)
        output.close()


def findallfile(base):
    for root,ds,fs in os.walk(base):
        for f in fs:
            filename = os.path.join(root, f)
            replace_dimen(filename)

def main():
    base = '/Users/shijiezhang/project/android_platform/bbs/module_community'
    findallfile(base)

if __name__ == '__main__':
    main()
