# -*- coding: utf-8 -*-

import sys
from openpyxl import Workbook


if __name__ == '__main__':
    wb = Workbook()
    ws = wb.active
    ws.append([1, 2, 3])
    wb.save(sys.argv[1] + "\\" + sys.argv[2])
