import math

def f (x):
    return pow(x,3) - 2 * pow(x,2) - 19 * x - 20

def f1 (x):
    return 3 * pow(x,2) - 4 * x - 19

def f2 (x):
    return pow(x,3) - 2 * pow(x,2) - 19 * x - 20

def function(a, b, e):
    while True:

      if (math.fabs(b - a) > e):
        break
      else:
        if(f(a) * f2(a) < 0):
            a = a + (b - a) / (f(a) - f(b))*f(a)
        elif(f(a) * f2(a) > 0):
            a = a - f(a) / f1(a)
            b = b + (b - a) / (f(b) - f(a))*f(b)
        else:
            print "can't solve"
            break
    print (a + b) / 2.0

def checker(a, b, e):

function(-2,3,0.00001)
