#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <vector>
#include <iostream>

using namespace std;

#define  MAX_CHAR 100000

void strToNum(char *s, float *v) {
    *v = atof(s);
}

void strToNum(char *s, int *v) {
    *v = atoi(s);
}

void strToNum(char *s, string *so) {
    so->append(s);
}

template <class T>
int readItems(FILE *f, vector<T> *v) {
    char buff[MAX_CHAR+1] = {0};
    fgets(buff, MAX_CHAR, f);
    //printf("buff %s\n", buff);
    char *pTok = strtok(buff, " \n");
    while (pTok != NULL)
    {
        T vl;
        strToNum(pTok, &vl);
        //cout << vl << endl;
        v->push_back(vl);
        pTok = strtok(NULL, " \n");
    }

    return v->size();
}


int solve() {
    FILE *fin = stdin;
    FILE *fout  = stdout;

    int nTest = 0;
    vector<int> vidx;
    vector<string> vin;
    vector<string> vout;
    vidx.reserve(4096);
    vin.reserve(4096);
    vout.reserve(4096);

    fscanf(fin, "%d\n", &nTest);
    fscanf(fin, "\n");

    while(nTest-- > 0) {
        //         readInts(fin, &vidx);
        //         readFloats(fin, &vin);
        readItems(fin, &vidx);
        //printf("vidx: %d\n", vidx.size());
        readItems(fin, &vin);
        //printf("vin: %d\n", vin.size());

        vout.resize(vidx.size(), "");
        //printf("vout: %d\n", vout.size());
        for (int i = 0; i < vidx.size(); i++) {
            vout[vidx[i]-1] = vin[i];
        }

        //printf("vout: %d\n", vout.size());
        //printf("vout capacity: %d\n", vout.capacity());
        for (int i = 0; i < vout.size(); i++) {
            fprintf(fout, "%s\n", vout[i].c_str());
        }

        if (nTest) {
            printf("\n");
            scanf("\n");
        }
        vidx.clear();
        vin.clear();
        vout.clear();
    }

    fclose(fin);
    fclose(fout);

    return 0;
}

int main() {
    return solve();
}
