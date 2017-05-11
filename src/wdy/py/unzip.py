# coding=utf-8
import os
import os.path
import shutil
import sys
import zipfile


def un_zip(file_name, dir):
    """unzip zip file"""
    zip_file = zipfile.ZipFile(file_name)
    if os.path.isdir(dir):
        pass
    else:
        os.mkdir(dir)
    for names in zip_file.namelist():
        zip_file.extract(names, dir)
    zip_file.close()


if __name__ == '__main__':
    isExists = os.path.exists(sys.argv[2] + "\\HDR-system")

    if isExists:
        # 如果存在则删除原有目录
        shutil.rmtree(sys.argv[2] + "\\HDR-system")

    os.makedirs(sys.argv[2] + "\\HDR-system")
    os.makedirs(sys.argv[2] + "\\HDR-system\\src")
    os.makedirs(sys.argv[2] + "\\HDR-system\\stdNum")
    os.makedirs(sys.argv[2] + "\\HDR-system\\stdScore")

    un_zip(sys.argv[1], sys.argv[2] + "\\HDR-system\\src")
