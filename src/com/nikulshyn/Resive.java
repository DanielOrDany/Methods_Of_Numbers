// Example program
#include <iostream>
#include <string>
#include <math.h>
        using namespace std;

        int main()
        {

        double x[2] = {-1, -1};
        double y[2] = {-1, -1};
        double J[2][2];
        double f[2];
        double J_[2][2];
        double ee = 0.00001;
        double dd;
        double X[2];
        double Y[2];
        double B[2];
        double P[2];
        double V[2][2];
        bool cond_N, cond_Z;
        int n = 2;

        do
        {

        cond_N = false;
        f[0] = x[0] * x[1] - x[1] * x[1] + 0.1 - x[0];
        f[1] = 2 * x[0] * x[1] + 0.1 - x[1];
        J[0][0] = 2 * x[0] - 1;
        J[1][0] = 2 * x[1];
        J[0][1] = -2 * x[1];
        J[1][1] = 2 * x[0] - 1;

        for (int i=0; i<2; i++)
        {
        for (int j=0; j<2; j++)
        if (i == j)  B[j] = 1;
        else  B[j] = 0;

        for (int i=0; i<n; i++) X[i] = B[i] = Y[i];

        for (int k=0; k<n; k++)
        for (int j=0; j<n; j++)
        if (k != j)
        {
        P[k] = B[k]/J[k][k];
        V[k][j] =- J[k][j]/J[k][k];
        }
        else V[k][j] = 0;

        do
        {
        cond_Z = false;
        for (int k=0; k<n; k++)
        {
        dd = 0;
        for (int j = 0; j<n; j++) dd += V[k][j] * X[j];
        X[k] = P[k] + dd;
        }

        for (int k=0; k<n; k++)
        {
        cond_Z = cond_Z || fabs((X[k]-Y[k])/X[k]*100) > ee;
        Y[k] = X[k];
        }
        }
        while (cond_Z);
        for (int j=0; j<2; j++) J_[j][i]=X[j];
        }

        for (int i=0; i<n; i++)
        {
        dd=0;
        for (int j=0; j<n; j++) dd+=J_[i][j]*f[j];
        x[i]= y[i] - dd;
        cout << x[i];
        }
        for (int i=0; i<n; i++)
        {
        cond_N = cond_N || fabs((x[i]-y[i])/x[i]*100) > ee;
        y[i] = x[i];
        }
        }
        while (cond_N);

        f[0] = x[0] * x[1] - x[1] * x[1] + 0.1 - x[0];
        f[1] = 2 * x[0] * x[1] + 0.1 - x[1];

        cout << f[0];
        cout << f[1];
        }
