# coding=utf-8
import numpy as np
from sklearn.externals import joblib
from pandas.algos import int32
import time


def read_usps_dataSet():
    filename = 'dataset/zip.test'
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
    print(time.strftime('%Y-%m-%d %H:%M:%S'))

    td, tl = read_usps_dataSet()
    usps_clf = joblib.load('model/usps_clf.model')

    predictions = [int(a) for a in usps_clf.predict(td)]
    num_correct = sum(int(a == y) for a, y in zip(predictions, tl))
    print("%s of %s test values correct." % (num_correct, len(tl)))

    print(time.strftime('%Y-%m-%d %H:%M:%S'))