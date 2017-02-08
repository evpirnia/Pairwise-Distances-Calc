# Pairwise-Distances-Calc
Assignment 2 of ICS 475 </br>
Given a file in the Clustal Omega alignment format, determine the distance for all pairwise alignment induced by the multiple sequence alignment. The distance between two sequences can simply be computed as the sum of all their mismatching columns, i.e. match = 0, mismatch and gap = 1.

### Instructions
$ javac pairseqdist.java </br>
$ java pairseqdist sampleInput.txt

Note: A sample text file known as sampleInput.txt is included that has the Clustal Omega alignment format.

### Example of program's output (Forward, 5’)
Distance (sp|P13987|CD59_HUMAN, sp|P27274|CD59_RAT) = A </br>
Distance (sp|P13987|CD59_HUMAN , sp|Q8SQ46.1|CD59_MACFA ) = B </br>
Distance (sp|P13987|CD59_HUMAN , tr|A0A0B5JW41|A0A0B5JW41_DANRE ) = C</br>
Distance (sp|P27274|CD59_RAT, sp|Q8SQ46|CD59_MACFA) = D </br>
Distance (sp|P27274|CD59_RAT, tr|A0A0B5JW41|A0A0B5JW41_DANRE) = E </br> Distance (sp|Q8SQ46.1|CD59_MACFA, tr|A0A0B5JW41|A0A0B5JW41_DANRE) = F</br>
Smallest distance = G </br>
Closest to Human is H </br>

Note: A through G are integer values. H is the name of a organism.
