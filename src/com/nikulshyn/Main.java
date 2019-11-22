package com.nikulshyn;

    public class Main {
        public static void main(String[] args) {
            double[] x = {-1, -1};
            double[] y = {-1, -1};
            double[][] J = new double[2][2];
            double[] f = new double[2];
            double[][] J_ = new double[2][2];
            double ee = 0.00001;
            double dd;
            double[] X = new double[2];
            double[] Y = new double[2];
            double[] B = new double[2];
            double[] P = new double[2];
            double[][] V = new double[2][2];
            boolean cond_N, cond_Z;
            int n = 2;

            do {
                cond_N = false;
                f[0] = x[0] * x[1] - x[1] * x[1] + 0.1 - x[0];
                f[1] = 2 * x[0] * x[1] + 0.1 - x[1];
                J[0][0] = 2 * x[0] - 1;
                J[1][0] = 2 * x[1];
                J[0][1] = -2 * x[1];
                J[1][1] = 2 * x[0] - 1;

                for (int i=0; i<2; i++){
                    for (int j=0; j<2; j++){
                        if (i == j) {
                            B[j] = 1;
                        } else {
                            B[j] = 0;
                        }
                    }

                    for (int i_=0; i_<n; i_++){
                        X[i_] = B[i_];
                        Y[i_] = B[i_];
                    }

                    for (int k=0; k<n; k++){
                        for (int j_=0; j_<n; j_++){
                            if (k != j_){
                               P[k] = B[k]/J[k][k];
                               V[k][j_] =- J[k][j_]/J[k][k];
                            } else {
                                V[k][j_] = 0;
                            }
                        }
                    }

                    do {
                        cond_Z = false;
                        for (int k=0; k<n; k++){
                            dd = 0;
                            for (int j = 0; j<n; j++){
                                dd += V[k][j] * X[j];
                                X[k] = P[k] + dd;
                            }
                            for (int k_=0; k_<n; k_++){
                                cond_Z = cond_Z || Math.abs((X[k]-Y[k])/X[k]*100) > ee;
                                Y[k] = X[k];
                            }
                        }
                    } while (cond_Z);

                    for (int j=0; j<2; j++){
                        J_[j][i]=X[j];
                    }
                }

                for (int i=0; i<n; i++){
                    dd=0;
                    for (int j=0; j<n; j++){
                        dd+=J_[i][j]*f[j];
                    }
                    x[i]= y[i] - dd;
                }
                for (int i=0; i<n; i++){
                    cond_N = cond_N || Math.abs((x[i]-y[i])/x[i]*100) > ee;
                    y[i] = x[i];
                }
            } while (cond_N);

            for (int i=0; i<n; i++){
                System.out.println(f[i]);
            }

            f[0] = x[0] * x[1] - x[1] * x[1] + 0.1 - x[0];
            f[1] = 2 * x[0] * x[1] + 0.1 - x[1];
            System.out.println(f[0]);
            System.out.println(f[1]);
        }
    }

