//Course: CS4413
//Student Name: Richard Hale
//Student ID: 000153021
//Assignment #: 1
//Due Date: October 3, 2011
//Signature: ____________________
//Score: 

#include "stdafx.h"
#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

//instantiate default functions
vector <int> merge (const vector <int> &sub1, const vector <int> &sub2);
vector <int> mergesort (const vector <int> &input);

vector<int> merge (vector<int> &sub1, vector<int> &sub2){ //merge two sublists, takes two sublists and the lists together as input, plus the size of each sublist
	int i, j, k;
	vector<int> original(sub1.size()+sub2.size());
	i=0;j=0;k=0; //counters
	while(i<sub1.size() && j<sub2.size()){ //repopulate the original list starting with lowest elements in each sublist
		if(sub1[i] < sub2[j]){
			original[k] = sub1[i];
				cout << sub1[i]<<endl;
			i++;
		}
		else {
			original[k] = sub2[j];
				cout << sub2[j]<<endl;
			j++;
		}
		k++;
	}
	cout << k << " comparisons were made." << endl;
	if(i>=sub1.size()) //if more elements from sublist 1 were added to the original list than sublist 2, populate the rest of the original list with values from sublist 2
		for(int a = j;a <sub2.size(); a++){
			original[k]=sub2[a];
			k++;
		}
	else //otherwise, populate the original list with all remaining values in sublist 1
		for(int b = i; b<sub1.size();b++){
			original[k]=sub1[b];
			k++;
		}
		
		cout << "Sorted list result: \n";
for(int z=0; z<original.size(); z++)
	cout<<original[z]<<endl;
return original;
}
vector<int> mergesort (vector<int> &input){ //called when user selects a list to mergesort, takes the list itself and its size as input
	if(input.size()>1){
		int h = (input.size()/2); int m = ((input.size()) - h); vector<int> sub1 (h); vector<int> sub2 (m);
		cout << "Creating new sublist A of elements: "<< endl;
		for(int i = 0; i < h; i++){ //populate a sublist of the first n/2 elements in input
			sub1[i] = input[i];
			cout<<"index " << i << " is " << sub1[i]<<"\n";
		}
		cout << "Creating new sublist B of elements: "<< endl;
		for(int j = 0; j < m; j++){ //populate a sublist of the last n/2 elements in input
			sub2[j] = input[j+m];
			cout<<"index " << j << " is " << sub2[j]<<"\n";
		}
	sub1 = mergesort(sub1); //recursively split the sublist again into smaller sublists
	sub2 = mergesort(sub2); // The sublists are now the result of all previous levels of recursion run through the merge function
	return (merge(sub1, sub2)); //merge current sublists, this is called once for each recursive call + 1
	}
	else return input; //return current list if it has 1 or fewer elements
}


int main()
{
	cout << "Mergesort program by Richard Hale\n";
	int i; int n = 0;
	double rand=0.0; char c; 
	cout << "\nFor a list of pseudo-random numbers in no order, press r\nFor a list of pseudo-random numbers in decreasing order, press d\nFor a list of pseudo-random numbers in increasing order, press i\n";
	cin >> c; //ask user to choose a list of numbers to mergesort
	switch (tolower(c)){
		case 'r':{ //user chooses a list of numbers in no order
			ifstream fileread ("noorder.txt");
			vector<int> nums (16); //vector of text file input
			if (fileread.is_open())
			{
				 while ( fileread.good() && n<16)
				 {
					 fileread >> i;
					 nums[n] = i;
					  cout << nums[n]<<endl;
					 n++;
				 }
			fileread.close();
		   }
			cout << "There are " << nums.size() << " elements to be sorted." << endl;
			system ("PAUSE");
			mergesort(nums);
			break;}
		case 'd':{ //user chooses a decreasing order text file list
			ifstream fileread2 ("decreasing.txt");
			vector<int> nums2(32); //vector of text file input
			if (fileread2.is_open())
			{
				 while ( fileread2.good() && n<32)
				 {
					 fileread2 >> i;
					 nums2[n] = i;
					 cout << nums2[n]<<endl;
					 n++;
				 }
			fileread2.close();
		   }
			cout << "There are " << nums2.size() << " elements to be sorted." << endl;
			system ("PAUSE");
			mergesort(nums2);
			break;}
		case 'i':{ //user chooses an increasing order text file list
			ifstream fileread3 ("increasing.txt");
			vector<int> nums3(8); //vector of text file input
			if (fileread3.is_open())
			{
				 while ( fileread3.good() && n<8)
				 {
					 fileread3 >> i;
					 nums3[n] = i;
					 cout << nums3[n]<<endl;
					 n++;
				 }
			fileread3.close();
		   }
			cout << "There are " << nums3.size() << " elements to be sorted." << endl;
			system ("PAUSE");
			mergesort(nums3);
			break;}
	}
	system ("PAUSE");
	return 0;
}
