#!/usr/bin/python3

#convert the input/output files to bash generatable strings

def strify(file):
	ress=""
	with open(file, "rb") as f:
		byte = f.read(1)
		if byte.decode("utf-8")=='\t':
			ress += "\\t"
		elif byte.decode("utf-8")=='\n':
			ress += "\\n"
		else:
			ress += byte.decode("utf-8")

		while byte:
			byte = f.read(1)
			if byte.decode("utf-8")=='\t':
				ress += "\\t"
			elif byte.decode("utf-8")=='\n':
				ress += "\\n"
			else:
				ress += byte.decode("utf-8")
	return ress


cnt=5

with open("res.txt", "w") as resf:

	for i in range(1,cnt+1):
		inf =  "input"+str(i)+".txt"
		ouf = "output"+str(i)+".txt"
		#generate bashified line
		resf.write(strify(inf)+"\n")
		resf.write(strify(ouf)+"\n")
