# -*- coding: utf-8 -*-

import sys
import xlwt


if __name__ == '__main__':
    data = xlwt.Workbook()
    table = data.add_sheet(u"学生成绩")

    file1 = open(sys.argv[1] + "\\HDR-system\\stdNum\\num.txt")
    file2 = open(sys.argv[1] + "\\HDR-system\\stdScore\\score.txt")

    table.write(0, 0, u"学号")
    table.write(0, 1, u"分数")

    num = 1
    while 1:
        line1 = file1.readline()
        line2 = file2.readline()
        if not line1 and not line2:
            break
        pass
        table.write(num, 0, line1)
        table.write(num, 1, line2)
        num += 1

    file1.close()
    file2.close()

    data.save(sys.argv[1] + "\\" + sys.argv[2])
