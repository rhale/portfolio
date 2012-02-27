import java.util.*;
import java.io.*;

public class CPU extends Thread {

    private int pc = 0;
    private String hex;
    private String bin;
    private int type;
    private String[] registers = new String[16];
    private String[] cache = new String[30];
    boolean free = false;
    RAM RAM = new RAM();     //Testing only

    public boolean isFree() {
        return free;
    }

    public void setFreedom(boolean freedom) {
        free = freedom;
    }

    public void setCache(String hex, int index) {
        cache[index] = hex;
    }

    public void fetch() {
        hex = cache[pc];
    }

    public void decode() {
        int dec = converter.hexToDec(hex);
        bin = converter.decToBin(dec);

        if (bin.substring(0, 2).equals("00")) {
            type = 0;
        } else if (bin.substring(0, 2).equals("01")) {
            type = 1;
        } else if (bin.substring(0, 2).equals("10")) {
            type = 2;
        } else {
            type = 3;
        }
    }

    public void execute() {
        switch (type) {
            case 0: //R format
                int opCode = converter.binToDec(bin.substring(2, 8));
                int sReg = converter.binToDec(bin.substring(8, 12));
                int sReg2 = converter.binToDec(bin.substring(12, 16));
                int dReg = converter.binToDec(bin.substring(16, 20));
                switch (opCode) {
                    case 4://Move from reg1 to reg2.
                        //Move to destination
                        break;
                    case 5://Add reg1 to reg2 and put in destination.
                        registers[dReg] = converter.decToBin(converter.binToDec(registers[sReg]) + converter.binToDec(registers[sReg2]));
                        break;
                    case 6://Subtraction reg1 - reg2 and put in destination.
                        registers[dReg] = converter.decToBin(converter.binToDec(registers[sReg]) - converter.binToDec(registers[sReg2]));
                        break;
                    case 7://Multiplies regs
                        registers[dReg] = converter.decToBin(converter.binToDec(registers[sReg]) * converter.binToDec(registers[sReg2]));
                        break;
                    case 8://Divides regs
                        registers[dReg] = converter.decToBin(converter.binToDec(registers[sReg]) / converter.binToDec(registers[sReg2]));
                        break;
                    case 9://Logical AND of reg1 and reg2.
                        if (converter.binToDec(registers[sReg]) == 1 && converter.binToDec(registers[sReg2]) == 1) {
                            registers[dReg] = converter.decToBin(1);
                        } else {
                            registers[dReg] = converter.decToBin(0);
                        }
                        break;
                    case 10://Logical OR
                        if (converter.binToDec(registers[sReg]) == 1 || converter.binToDec(registers[sReg2]) == 1) {
                            registers[dReg] = converter.decToBin(1);
                        } else {
                            registers[dReg] = converter.decToBin(0);
                        }
                        break;
                    case 16://Sets destination register to 1 if reg1 is less than reg2 and 0 otherwise.
                        if (converter.binToDec(registers[sReg]) < converter.binToDec(registers[sReg2])) {
                            registers[dReg] = converter.decToBin(1);
                        } else {
                            registers[dReg] = converter.decToBin(0);
                        }
                        break;
                }
                break;
            case 1: //I format
                opCode = converter.binToDec(bin.substring(2, 8));
                int bReg = converter.binToDec(bin.substring(8, 12));
                dReg = converter.binToDec(bin.substring(12, 16));
                int address = converter.binToDec(bin.substring(16));
                switch (opCode) {
                    case 2://?//Store content of base reg into given address
                        if (address != 0) {
                            RAM.saveByIndex(registers[bReg], address / 4);
                        } else {
                            RAM.saveByIndex(registers[bReg], dReg / 4);
                        }
                        //Divide address by 4 and add the ram address. Convert from base into hex and send to the address just calculated.
                        //else
                        //D-reg in decimal/4 + ramAdd. Convert the base into hex and send to address calculated.
                        break;
                    case 3://Loads content of given address into given register.
                        if (bReg == 0) {
                            registers[dReg] = converter.decToBin(address);
                        } else {
                            registers[dReg] = converter.decToBin(bReg);
                        }
                        break;
                    //Send to the destination register, the address from hex to binary + ramAddress.
                    //else
                    //Send to the destination register, the base register from hex to binary + ramAddress.
                    case 11://Stores data given by the adddress in the given register.
                        if (address != 0) {
                            registers[dReg] = converter.decToBin(address);
                        }
								else {
                            registers[bReg] = converter.decToBin(address);
                        }
                        break;
                    //Stores the data from the address (hex to bin) into the destination register. [As long as it's not 0]
                    //else
                    //Stores the data from the address (hex to bin) into base register.
                    case 12://Add immediate data from address to register.
                        if (dReg == 0) {
                            registers[bReg] = converter.decToHex(converter.hexToDec(registers[bReg]) + address);
                        } else {
                            registers[dReg] = converter.decToHex(converter.hexToDec(registers[bReg]) + address);
                        }
                        break;
                    //Convert data in destination to decimal and then add the address field data to it and put it back in the destination register.
                    //else
                    //Same except using base register.
                    case 13:
                        if (dReg == 0) {
                            registers[bReg] = converter.decToHex(converter.hexToDec(registers[bReg]) * address);
                        } else {
                            registers[dReg] = converter.decToHex(converter.hexToDec(registers[bReg]) * address);
                        }
                        break;
                    case 14:
                        if (dReg == 0) {
                            registers[bReg] = converter.decToHex(converter.hexToDec(registers[bReg]) / address);
                        } else {
                            registers[dReg] = converter.decToHex(converter.hexToDec(registers[bReg]) / address);
                        }
                        break;
                    case 15://Store given address into a register.
                        if (dReg != 0) {
                            registers[dReg] = converter.decToHex(address);
                            
                        } else {
                            registers[bReg] = converter.decToHex(address);
                            
                        }
                        break;
                    //Convert the address binary and store into destination register.
                    //else
                    //Same with base.
                    case 17:
                        if (bReg < address) {//Checking base register > or < to address.
                            dReg = 1;//If base register less than value given by addresss, then destination register = 1.
                        } else {
                            dReg = 0;
                        }
                        break;
                    case 21:
                        if (bReg == dReg) {
                            pc = address / 4;//If base and register are same, pc = address/4.
                        } else {
                            pc++;
                        }
                        break;
                    case 22:
                        if (bReg != dReg) {
                            pc = address / 4;//If base and register are same, pc = address/4.
                        } else {
                            pc++;
                        }
                        break;
                    case 23://Branches to given address if content of base register is = 0.
                        //If base register = 0, do pc = address/4
                        //else
                        //pc++
                        if (dReg == 0) {
                            pc = address / 4;//If base and register are same, pc = address/4.
                        } else {
                            pc++;
                        }
                        break;
                    case 24://Do for !=0
                        if (bReg != 0) {
                            pc = address / 4;
                        } else {
                            pc++;
                        }
                        break;
                    case 25://Do for > 0
                        if (bReg > 0) {
                            pc = address / 4;
                        } else {
                            pc++;
                        }
                        break;
                    case 26://Do for < 0
                        if (bReg < 0) {
                            pc = address / 4;
                        } else {
                            pc++;
                        }
                        break;
                }
                break;
            case 2: //J format
                opCode = converter.binToDec(bin.substring(2, 8));
                address = converter.binToDec(bin.substring(8));
                switch (opCode) {
                    case 18://Halt
                        free = true;//cpu is now free
                        break;
                }
                break;
            case 3: //I/O format
                opCode = converter.binToDec(bin.substring(2, 8));
                int reg1 = converter.binToDec(bin.substring(8, 12));
                int reg2 = converter.binToDec(bin.substring(12, 16));
                address = converter.binToDec(bin.substring(16));
                switch (opCode) {
                    case 0:
						  		if(reg1!=0)
									registers[reg1]=registers[address/4+converter.binToDec(cache[address])];
								else if(reg2!=0)
									registers[reg2]=converter.decToBin(address/4+converter.binToDec(cache[address]));
								//CPU
								pc++; //?
								break;
						  //Reads content of input.
                    //Takes current proccess address/4 + current process ramAddress, convert to binary, and store it into reg1.
                    //else
                    //Check reg2 for an address and do same thing above.
                    //set cpu and pc
                    case 1:
						  		if(reg1 != 0)
									registers[address/4+converter.binToDec(cache[address])] = registers[reg1];
								else if(reg2 != 0)
									registers[address/4+converter.binToDec(cache[address])] = converter.decToBin(reg2);
								break;
                    //reg1  address/4 + ramAddress
                    //Set the value of reg1 into the ram. (address/4 + ramAddress)
                    //else
                    //Set the value of reg2 into the ram. (address/4 + ramAddress)
                }
                break;
        }
    }

    public void run() {
        while (true) {
            while (!free) {
                fetch();
                decode();
                execute();
            }
        }
    }
}
