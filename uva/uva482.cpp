#include <vector>

using namespace std;

#define MAX 10

int solve() {
	FILE *f = open("input", "r");
	int n = 0;
	vector<int> vindexs[MAX];
	vector<float> vin[MAX];
	vector<float> vout[MAX];
	fscanf(f , "%d", &n);
	for (int i = 0; i < n; i++) {
		int idx = 0;
		while(fscanf(f, "%d", &idx) > 0) {
			vindexs[i].push(idx);
		}
		float v = 0.0;
		while(fscanf(f, "%f", &v) {
			vin[i].push(v);
		}		
	}
	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < vindexs[i].size(); j++)
			vout[i][vindexs[j]] = vin[i][j];
	}
	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < vindexs[i].size(); j++)
			fprintf(f,"%f\n", vout[i][j]);
		fprintf(f,"\n");
	}
	
	fclose(f);
}