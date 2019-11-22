#include <iostream.h>
#include <math.h>
double Nuton(double*p,int n,double*x,double X)
{
double s=p[n-1];
for(int i=n-2;i>=0;i--) s=p[i]+(X-x[i])*s;
return s;
}
void Nuton1(double*x,double*y,int n,double*p)
{
for(int j=0;j<n-1;j++) {
p[j]=(y[j+1]-y[j])/(x[j+1]-x[j]);
// cout<<p[j]<<"\t";
}
// cout<<"\n";
int m=n-1;
int l=2;
int k;
for(int i=n-2;i>=1;i--)
{
k=0;
for(j=1;j<=i;j++)
{
p[m]=(p[m-i]-p[m-i-1])/(x[k+l]-x[k]);
// cout<<p[m]<<"\t";
m++;
k++;
}
// cout<<"\n";
l++;
}
j=0;
for(i=1;i<n;i++)
{
y[i]=p[j];
j=j+(n-i);
}

}
void main()
{
int const n=5,N=(n-1)*n/2;
double x[]={1,2,3,4,5};
double y[n];
for(int i=0; i<n; i++) y[i]=1+2*x[i]+3*pow(x[i],2)+4*pow(x[i],3)+5*pow(x[i],4);
double *p=new double[N];
Nuton1(x,y,n,p);
//for (i=0;i<n;i++) cout<<y[i]<<'\t';
double z=1.5;
cout<<Nuton(y,n,x,z)<<'\t'<<1+2*z+3*pow(z,2)+4*pow(z,3)+5*pow(z,4)<<'\n';

}