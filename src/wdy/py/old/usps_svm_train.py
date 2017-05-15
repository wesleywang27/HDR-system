# coding=utf-8
import numpy as np
from sklearn.svm import NuSVC
from sklearn.externals import joblib
from pandas.algos import int32
import time


def read_usps_dataSet():
    filename = 'dataset/zip.train'
    fr = open(filename)
    numberOfLines = len(fr.readlines())
    trainDataMat = np.zeros((numberOfLines, 256))  # prepare matrix to return
    trainLabelMat = np.zeros(numberOfLines, dtype=int32)
    fr = open(filename)
    index = 0
    for line in fr.readlines():
        line = line.strip()  # delete the /r/n
        listFromLine = line.split(' ')
        label = listFromLine[0].split('.')
        trainLabelMat[index] = int(label[0])

        arr = []
        for i in range(1, len(listFromLine)):
            arr.append(listFromLine[i])
        arr = np.array(arr, dtype=np.float32, order="C", copy=True)
        trainDataMat[index] = arr
        index += 1
    return trainDataMat, trainLabelMat


if __name__ == "__main__":
    td, tl = read_usps_dataSet()
    print(time.strftime('%Y-%m-%d %H:%M:%S'))

    usps_clf = NuSVC(nu=0.02, kernel='rbf', gamma=0.02)

    usps_clf.fit(td, tl)
    print(time.strftime('%Y-%m-%d %H:%M:%S'))
    joblib.dump(usps_clf, 'model/usps_clf.model')
