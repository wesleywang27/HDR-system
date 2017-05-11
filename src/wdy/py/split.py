# coding=utf-8

from PIL import Image
import cv2
from cv2 import cv
from pylab import *
import numpy as np


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
    # print num
    ''' num大于3说明四个角是白色的，否则是黑色'''
    if num >= 3:
        # 把原来颜色反转后加强
        ret, bin = cv2.threshold(gray, 100, 255, cv2.THRESH_BINARY_INV)
    else:
        # 保持原来颜色不变，只是加强
        ret, bin = cv2.threshold(gray, 100, 255, cv2.THRESH_BINARY)
    # cv2.imshow("bin",bin)
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
        # rc[0] 表示图像左上角的纵坐标，rc[1] 表示图像左上角的横坐标，rc[2] 表示图像的宽度，rc[3] 表示图像的高度，
        # cv2.rectangle(bin, (rc[0],rc[1]),(rc[0]+rc[2],rc[1]+rc[3]),(255,0,255))
        image1M = cv.fromarray(median)
        image1Ip = cv.GetImage(image1M)
        cv.SetImageROI(image1Ip, rc)
        imageCopy = cv.CreateImage((rc[2], rc[3]), cv2.IPL_DEPTH_8U, 1)
        cv.Copy(image1Ip, imageCopy)
        cv.ResetImageROI(image1Ip)
        # print np.asarray(cv.GetMat(imageCopy))
        # 把图像左上角的纵坐标和图像的数组元素放到字典里
        dictPic[rc[0]] = np.asarray(cv.GetMat(imageCopy))
        pic.append(np.asarray(cv.GetMat(imageCopy)))
        # cv.ShowImage(str(i), imageCopy)
        # cv.Not(imageCopy, imageCopy)    #函数cvNot(const CvArr* src,CvArr* dst)会将src中的每一个元素的每一位取反，然后把结果赋给dst
        # cv.SaveImage(str(i)+ '.jpg',imageCopy)
        i = i + 1
    sortedNum = sorted(dictPic.keys())
    for i in range(len(sortedNum)):
        pic[i] = dictPic[sortedNum[i]]
    # cv2.waitKey(0)
    return pic


def resize(picArray, size):
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

        dir = u'../../tmp/split/'
        imgResize.save(dir + str(i) + '.jpg')


file = u'../../tmp/src/pic2.png'
pic = picSplitResize(file)
resize(pic, (32, 32))
