# coding=utf-8
import re
import os


def replace():
    i = 0
    path = r"/Users/Scott/Documents/soyoungGit/pygit/base/common_widget/src/main/res/layout"
    # path = r"/Users/Scott/Documents/soyoungGit/library/library_widgets/src/main/res/layout"
    file_list = os.listdir(path)
    for files in file_list:
        i = i + 1
        old_dir = os.path.join(path, files)
        if os.path.isdir(old_dir):
            continue
        filename = os.path.splitext(files)[0]
        filetype = os.path.splitext(files)[1]
        file_path = os.path.join(path, filename + filetype)
        findplace(file_path)


# 规则，文本包含 "@dimen/d_xx" 或"@dimen/dp_xx" 替换成 "xxdp"
# bug 目前会把@dimen/sp_xx 也替换成 xxdp
def findplace(file):
    reg_list = {'@dimen/d_(.*?)\"': r'\1dp"', '@dimen/dp_(.*?)\"': r'\1dp"', '@dimen/sp_(.*?)\"': r'\1sp"'}
    pattern = '@dimen.(.*?)\"'  # 这一行是否有 "@dimen/d_43" "@dimen/dp_23"  "@dimen/sp_13"
    # 方法一，文件有没有都会重写
    # with open(file, "r") as f1, open("%s.bak" % file, "w") as f2:
    #     for line in f1:
    #         re1 = re.compile(pattern)
    #         m = re1.search(line)
    #         if m:
    #             for key in reg_list:
    #                 line = re.sub(key, reg_list[key], line)
    #         f2.write(line)
    # os.remove(file)
    # os.rename("%s.bak" % file, file)
    #     方法二，直接修改文件，不需要每个文件都填写
    file_data = ""
    if_modify = False
    with open(file, "r") as f:
        for line in f:
            re1 = re.compile(pattern)
            m = re1.search(line)
            if m:
                if_modify = True
                for key in reg_list:  # 循环匹配替换d_,dp_,sp_
                    line = re.sub(key, reg_list[key], line)
            file_data += line
    if if_modify:
        print 'replcae file=', file
        with open(file, "w") as f:
            f.write(file_data)



if __name__ == '__main__':
    replace()
