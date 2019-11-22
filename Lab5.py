from math import sin, pi
import matplotlib.pylab as plt

U_max = 100
frequency = 50
R1 = 5
R2 = 4
R3 = 7
C1 = 300 * 10 ** -6
C2 = 150 * 10 ** -6

f = [
    lambda t,value: ((U_max * sin(2 * pi * frequency * t)-value[0]-value[1]+R3)/(R1+R3))/C1,
    lambda t,value: (((U_max * sin(2 * pi * frequency * t)-value[0]-value[1]+R3)/(R1+R3)) - value[1])/C2
    ]


def output_voltage(value):
    return value[1]


def get_next_value(time_point, next_time_point, value, step):
    """Modified-Euler method"""
    next_value_corrected = next_value_predicted = value
    print(len(value))
    for i in range(len(value)):
        next_value_predicted[i] = value[i] + step * f[i](time_point, value)
        next_value_corrected[i] = value[i] + 0.5 * step * (f[i](time_point, value) + f[i](next_time_point, next_value_predicted))

    return next_value_corrected


def get_results(time_point, time_interval, value, step):
    time_value_pairs = dict()

    while time_point < time_interval:
        next_time_point = time_point + step
        next_value = get_next_value(time_point, next_time_point, value, step)

        time_point = next_time_point
        value = next_value

        time_value_pairs[time_point] = output_voltage(value)

    return time_value_pairs


def main():
    time_point = 0
    value = [0, 0]
    time_interval = 0.2
    step = 0.00001

    time_value_pairs = get_results(time_point, time_interval, value, step)

    time_points = []
    values = []
    for t, v in time_value_pairs.items():
        time_points.append(t)
        values.append(v)

    plt.plot(time_points, values)
    plt.show()


if __name__ == '__main__':
    main()