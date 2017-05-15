# coding=utf-8

from PIL import Image
import cv2
from cv2 import cv
from pylab import *
import numpy as np
import os
from os import walk
from scipy._lib.six import xrange


def get_stdNums(im):
    w, h = im.size
    i = 1
    data = list(im.getdata())

    for y in xrange(h):
        for x in xrange(w):
            if data[y * w + x] == 0:
                while x + i < w and data[y * w + x + i] == 0:
                    i += 1
                if i > 200:
                    return x, x + i, y
                i = 1
    return 0, 0, 0


def get_stdScores(im):
    w, h = im.size
    data = array(im)
    min_x = 0
    min_y = 0
    max_x = 0
    max_y = 0

    for y in xrange(h):
        for x in xrange(w):
            if data[y, x, 0] > 180 and data[y, x, 1] < 40 and data[y, x, 2] < 40:
                if x < min_x or min_x == 0:
                    min_x = x
                if x > max_x or max_x == 0:
                    max_x = x
                if y < min_y or min_y == 0:
                    min_y = y
                if y > max_y or max_y == 0:
                    max_y = y

    return min_x, min_y, max_x, max_y


def seg_stdNum(x1, y1, x2, y2, im, file):
    im.crop((x1, y1, x2, y2)).save(sys.argv[1] + "\\HDR-system\\src\\" + file + "_num.png")


def seg_stdScore(x1, y1, x2, y2, im, file):
    im.crop((x1-10, y1-10, x2+10, y2+10)).save(sys.argv[1] + "\\HDR-system\\src\\" + file + "_score.png")


def picSplitResize(path):
    image = cv2.imread(path)
    ''' 获取一张图片四个角的灰度值'''
    w, h = cv.GetSize(cv.fromarray(image))
    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    angleGray = [0] * 4
    angleGray[0] = gray[0][0]
    angleGray[1] = gray[0][w - 1]
    angleGray[2] = gray[h - 1][0]
    angleGray[3] = gray[h - 1][w - 1]
    num = 0
    for i in range(len(angleGray)):
        if angleGray[i] > 100:
            num += 1

    ''' num大于3说明四个角是白色的，否则是黑色'''
    if num >= 3:
        # 把原来颜色反转后加强
        ret, bin = cv2.threshold(gray, 100, 255, cv2.THRESH_BINARY_INV)
    else:
        # 保持原来颜色不变，只是加强
        ret, bin = cv2.threshold(gray, 100, 255, cv2.THRESH_BINARY)
    # 膨胀后腐蚀
    dilated = cv2.dilate(bin, cv2.getStructuringElement(cv2.MORPH_RECT, (2, 2)))
    eroded = cv2.erode(dilated, cv2.getStructuringElement(cv2.MORPH_RECT, (2, 2)))
    # 腐蚀后膨胀
    eroded = cv2.erode(eroded, cv2.getStructuringElement(cv2.MORPH_RECT, (2, 2)))
    dilated = cv2.dilate(eroded, cv2.getStructuringElement(cv2.MORPH_RECT, (2, 2)))
    # 细化
    median = cv2.medianBlur(dilated, 3)
    median1 = cv2.medianBlur(dilated, 3)
    # 轮廓查找,查找前必须转换成黑底白字
    contours, heirs = cv2.findContours(median1, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
    i = 0
    pic = []
    dictPic = {}
    for tours in contours:
        rc = cv2.boundingRect(tours)
        # rc[0] 表示图像左上角的纵坐标，rc[1] 表示图像左上角的横坐标，rc[2] 表示图像的宽度，rc[3] 表示图像的高度
        image1M = cv.fromarray(median)
        image1Ip = cv.GetImage(image1M)
        cv.SetImageROI(image1Ip, rc)
        imageCopy = cv.CreateImage((rc[2], rc[3]), cv2.IPL_DEPTH_8U, 1)
        cv.Copy(image1Ip, imageCopy)
        cv.ResetImageROI(image1Ip)
        # 把图像左上角的纵坐标和图像的数组元素放到字典里
        dictPic[rc[0]] = np.asarray(cv.GetMat(imageCopy))
        pic.append(np.asarray(cv.GetMat(imageCopy)))
        i = i + 1
    sortedNum = sorted(dictPic.keys())
    for i in range(len(sortedNum)):
        pic[i] = dictPic[sortedNum[i]]
    return pic


def resize(picArray, size, file):
    for i in range(len(picArray)):
        imgPIL = Image.fromarray(picArray[i])
        w, h = imgPIL.size

        if w > h:
            imgEmpty = Image.new('L', (int(1.4 * w), int(1.4 * w)), 0)
        else:
            imgEmpty = Image.new('L', (int(1.4 * h), int(1.4 * h)), 0)

        w1, h1 = imgEmpty.size
        imgEmpty.paste(imgPIL, ((w1 - w) / 2, (h1 - h) / 2))
        imgResize = imgEmpty.resize(size, Image.ANTIALIAS)

        if file.split('_')[1] == "num":
            dir = sys.argv[1] + "\\HDR-system\\stdNum\\"
        else:
            dir = sys.argv[1] + "\\HDR-system\\stdScore\\"
        imgResize.save(dir + file + "_" + str(i) + '.png')


if __name__ == '__main__':
    files = []
    for (dirPath, dirNames, fileNames) in walk(sys.argv[1] + "\\HDR-system\\src\\"):
        files.extend(fileNames)
        break

    for file in files:
        img = Image.open(sys.argv[1] + "\\HDR-system\\src\\" + file)
        gray_img = img.convert('1')
        name = file.split('.')

        s, e, h = get_stdNums(gray_img)
        if h != 0 and e != 0:
            seg_stdNum(s, 0, e, h, img, name[0])

        s_x, s_y, e_x, e_y = get_stdScores(img)
        if e_x != 0 and e_y != 0:
            seg_stdScore(s_x, s_y, e_x, e_y, img, name[0])

        os.remove(sys.argv[1] + "\\HDR-system\\src\\" + file)

    files = []
    for (dirPath, dirNames, fileNames) in walk(sys.argv[1] + "\\HDR-system\\src\\"):
        files.extend(fileNames)
        break

    for file in files:
        pic = picSplitResize(sys.argv[1] + "\\HDR-system\\src\\" + file)
        name = file.split('.')
        resize(pic, (32, 32), name[0])
