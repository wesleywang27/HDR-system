# -*- coding: utf-8 -*-

from PIL import Image
from pylab import *
import numpy as np
from sklearn.externals import joblib
import os


def recognize(dir):
    files = []
    for (dirPath, dirNames, fileNames) in os.walk(dir):
        files.extend(fileNames)
        break
    count = sum([len(files) for root, dirs, files in os.walk(dir)])
    lists = np.zeros((count, 784))
    num = 0

    for file in files:
        img = Image.open(dir + file)
        img = img.resize((28, 28), Image.ANTIALIAS)
        arr = array(img.convert('L'), 'f')

        new_arr = []
        for row in arr:
            for i in row:
                if i < 50:
                    i = 0
                else:
                    i /= 256
                new_arr.append(i)
        new_arr = np.array(new_arr, dtype=np.float64, order="C", copy=True)
        lists[num] = new_arr
        num += 1

    clf = joblib.load("src/wdy/model/clf.model")

    return clf.predict(lists)


if __name__ == '__main__':
    predicted = recognize(sys.argv[1] + "\\HDR-system\\stdNum\\")
    file = open(sys.argv[1] + "\\HDR-system\\stdNum\\num.txt", "w")
    file.write(predicted)
    file.close()

    predicted = recognize(sys.argv[1] + "\\HDR-system\\stdScore\\")
    file = open(sys.argv[1] + "\\HDR-system\\stdScore\\score.txt", "w")
    file.write(predicted)
    file.close()
