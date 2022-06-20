def getlist(filepath):
    vec = []
    with open(filepath) as fp:  
        line = fp.readline()
        while line:
            vec.append(line)
            line = fp.readline()
    return vec

a = getlist('out')
b = getlist('out2')

for i in range(1, len(a)):
    if a[i] != b[i]:
        print (i)
        print (a[i])
        print (b[i])
        break