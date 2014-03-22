#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <vector>
#include <map>
#include <iostream>
#include <algorithm>
#include <utility>
#include <ctype.h>

using namespace std;
#if !defined(ANLOG)
#	define DLOG
#else
#	define DLOG fprintf
#endif

#define  MAX_CHAR 100000

void strToNum(char *s, float *v);
void strToNum(char *s, int *v);
void strToNum(char *s, string *so);
template <class T>
int readItems(FILE *f, vector<T> *v);
char* trimLineR(char *s);
char* trimR(char *s);
char* trimL(char *s);
int stricmp(const char *a, const char *b);

struct Team {
	int id;
	int nSolved;
	int pen;
};

bool isHigher(const pair<int, Team> &a, const pair <int, Team> &b)
{
	if (a.second.nSolved != b.second.nSolved) return a.second.nSolved > b.second.nSolved;
	
	if (a.second.pen != b.second.pen) return a.second.pen > b.second.pen;
	
	return a.second.id < b.second.id;
}

int solve() {
    FILE *fin = stdin;
    FILE *fout  = stdout;	
	int nTour = 0;		
	
	fscanf(fin, "%d\n", &nTour);	
	DLOG(fin, "nTour %d\n", nTour);
	while (nTour--) {
		int id, pid, time;
		char vert;
		map<int, Team> mit;
		
		fscanf(fin,"\n");
		char line[100];
		// DLOG(fout, "haha\n", line);
		//char *aline = fgets(line, 100, fin);
		// char *aline = gets(line);
		// DLOG(fout, "%d\n", strlen(aline));
		//DLOG(fout, "%d\n", strlen(fgets(line, 100, fin)));
		// DLOG(fout, "%s\n", line);
		// DLOG(fout, "%s\n", aline);
		// DLOG(fout, "%s\n", gets(line));
		//return 0;
		char *pline = 0;
		while ( (pline = gets(line)) != NULL && strlen(trimLineR(pline)) > 0){
			//DLOG(fout, "%s ", line);
			//DLOG(fout, "\t%d\n", strlen(pline));
			sscanf(line, "%d %d %d %c", &id, &pid, &time, &vert);
			//DLOG(fout, "T %d Verdict %c\n", time, vert);
			Team *team =0 ;
			if (mit.find(id) != mit.end()) {
				team = &mit[id];
			}
			else {
				Team t = {0};
				t.id = id;
				mit[id] = t;
				team = &mit[id];
			}
			
			if (vert == 'C') {
				team->nSolved += 1;
				team->pen += time;
			} else if (vert == 'I'){
				team->pen += 20;
			}
		}
		
		vector< pair<int, Team> > vt;
		std::copy(mit.begin(), mit.end(), std::back_inserter(vt));
		std::sort(vt.begin(), vt.end(), isHigher);

		vector< pair<int, Team> >::iterator it = vt.begin();
		for (; it != vt.end(); it++)
		{
			fprintf(fout, "%d %d %d\n", it->second.id, it->second.nSolved, it->second.pen);
		}
		
		if (nTour > 0)
			fprintf(fout, "\n");
	}	
	
    //fclose(fin);
    //fclose(fout);

    return 0;
}

int main() {
    return solve();
}


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
    char *pTok = strtok(buff, " \n");
    while (pTok != NULL)
    {
        T vl;
        strToNum(pTok, &vl);
        v->push_back(vl);
        pTok = strtok(NULL, " \n");
    }

    return v->size();
}

char* trimLineR(char *s) {
	char *end = &s[strlen(s) - 1];
	while(*end == '\n' || *end == '\r')
		end--;
	*(++end)	= '\0';
	return s;
}

char* trimR(char *s) {
	char *end = &s[strlen(s) - 1];
	while(isspace(*end))
		end--;
	*(++end)	= '\0';
	return s;
}

char* trimL(char *s) {
	while(isspace(*s))
		s++;	
	return s;
}

int stricmp(const char *a, const char *b)
{
	while (*a != 0 && *b != 0 && toupper(*a) == toupper(*b)) {
		a++;
		b++;
	}
	return toupper(*a) - toupper(*b);	
}
