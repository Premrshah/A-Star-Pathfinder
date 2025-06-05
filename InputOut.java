
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.lang.Thread;

public class InputOut {

	int speed;
	int decimal;
	int colSize;

	public InputOut(int spe, int dec) {
		speed = spe;
		decimal = dec;
	}
	public InputOut() {
	}

	public static void main(String[] args) {
		InputOut IO = new InputOut(8, 2);
		// IO.O(1, "Hi im a number hehehe: 12.2341 do you like it???10.012364357892");
		// IO.table("Letter", "Number", 1, 8);
		// IO.table("A", "1.1111111");
		// IO.table("B", "2.2222222");
		// IO.table("C", "3.3333333");
		// IO.table("D", "4.4444444");
		// IO.table("E", "5.5555555");
	}

	public void table(String str1, String str2, int alignment, int colSize) {
		this.colSize = colSize;
		printTableRow(str1, str2, alignment);
	}

	public void table(String str1, String str2, int alignment) {
		printTableRow(str1, str2, alignment);
	}

	public void table(String str1, String str2) {
		printTableRow(str1, str2, 0);
	}

	public void printTableRow(String str1, String str2, int alignment) {
		String col1 = "", col2 = "";

		if (alignment == 0) {
			col1 += str1;
		}
		for (int idx = 0; idx < colSize - str1.length(); idx++) {
			col1 += " ";
			if (idx == (colSize - str1.length()) / 2 && alignment == 1) {
				col1 += str1;
			}
			if (idx == str1.length() && alignment == 2) {
				col1 += str1;
			}
		}

		if (alignment == 0) {
			col2 += str2;
		}
		for (int idx = 0; idx < colSize - str2.length(); idx++) {
			col2 += " ";
			if (idx == (colSize - str2.length()) / 2 && alignment == 1) {
				col2 += str2;
			}
			if (idx == str2.length() && alignment == 2) {
				col2 += str2;
			}
		}
		O(1, col1 + "|" + col2);
	}

	public String autoRound(String print, int dec) {
		String num = "";
		String str = "";
		String numbers = "0123456789.";
		for (int idx = 0; idx < print.length(); idx++) {
			if (numbers.contains(print.charAt(idx) + "")) {
				if (!".".equals("" + print.charAt(idx)) && numbers.length() != 0)
					num += print.charAt(idx);
			} else {
				if (num.length() != 0) {
					if (num.contains(".")) {
						str += Math.round(Double.parseDouble(num) * Math.pow(10, dec)) / Math.pow(10, dec);
					} else {
						str += num;
					}
					num = "";
					str += print.charAt(idx);
				} else {
					str += print.charAt(idx);
				}
			}
		}
		if (num.length() != 0) {
			if (num.contains(".")) {
				str += Math.round(Double.parseDouble(num) * Math.pow(10, dec)) / Math.pow(10, dec);
			} else {
				str += num;
			}
		}
		return str;
	}

	public void O(int mode, String print) {
		// int speed = 30;
		String num = "";
		String str = "";
		String numbers = "0123456789.";
		for (int idx = 0; idx < print.length(); idx++) {
			if (numbers.contains(print.charAt(idx) + "")) {
				if (!".".equals("" + print.charAt(idx)) && numbers.length() != 0)
					num += print.charAt(idx);
			} else {
				if (num.length() != 0) {
					if (num.contains(".")) {
						str += Math.round(Double.parseDouble(num) * Math.pow(10, decimal)) / Math.pow(10, decimal);
					} else {
						str += num;
					}
					num = "";
				}
				str += print.charAt(idx);
			}
		}
		if (num.length() != 0) {
			if (num.contains(".")) {
				str += Math.round(Double.parseDouble(num) * Math.pow(10, decimal)) / Math.pow(10, decimal);
			} else {
				str += num;
			}
		}
		if (mode == 1) {
			System.out.println(str);
		}
		if (mode == 2) {
			JOptionPane.showMessageDialog(null, str);
		}
		if (mode == 3) {
			int length = str.length();
			for (int i = 0; i < length; i++) {
				try {
					char letter = str.charAt(i);
					System.out.print(letter);
					Thread.sleep(speed);
				} catch (Exception e) {
					System.out.print("Error");
				}
			}
		}
	}

	public int II(int mode, String str) {
		if (mode == 1) {
			String data = JOptionPane.showInputDialog(str);
			while (data.equals("")) {
				data = JOptionPane.showInputDialog(str);
			}
			return (Integer.parseInt(data));
		}

		if (mode == 2) {
			Scanner inpt = new Scanner(System.in);
			System.out.print(str);
			return (inpt.nextInt());
		}
		return 0;
	}

	public double ID(int mode, String str) {
		if (mode == 1) {
			String data = JOptionPane.showInputDialog(str);
			while (data.equals("")) {
				data = JOptionPane.showInputDialog(str);
			}
			return (Double.parseDouble(data));
		}

		if (mode == 2) {
			Scanner inpt = new Scanner(System.in);
			System.out.print(str);
			return (inpt.nextDouble());
		}
		return 0;
	}

	public String IS(int mode, String str) {
		if (mode == 1) {
			String data = JOptionPane.showInputDialog(str);
			while (data.equals("")) {
				data = JOptionPane.showInputDialog(str);
			}
			return (data);
		}

		if (mode == 2) {
			Scanner inpt = new Scanner(System.in);
			System.out.print(str);
			return (inpt.nextLine());
		}
		return "Error!";
	}

	public String getArrayI(int mode, int[] list, int eleAdd) {
		String str = "";
		int idx;
		for (idx = 0; idx < list.length; idx++) {
			if (mode == 1) {
				if (idx == 0) {
					str = "(" + list[idx];
				} else if (idx == list.length - 1) {
					str = str + ", " + list[idx] + ")";
				} else {
					str = str + ", " + list[idx];
				}
			} else if (mode == 2) {

				if (idx == list.length - 1) {
					str = str + (idx + eleAdd) + "\t" + list[idx];
				} else {
					str = str + (idx + eleAdd) + "\t" + list[idx] + "\n";
				}
			}
		}
		return str;
	}

	public String getArrayD(int mode, double[] list, int eleAdd) {
		String str = "";
		int idx;
		for (idx = 0; idx < list.length; idx++) {
			if (mode == 1) {
				if (idx == 0) {
					str = "(" + list[idx];
				} else if (idx == list.length - 1) {
					str = str + ", " + list[idx] + ")";
				} else {
					str = str + ", " + list[idx];
				}
			} else if (mode == 2) {

				if (idx == list.length - 1) {
					str = str + (idx + eleAdd) + "\t" + list[idx];
				} else {
					str = str + (idx + eleAdd) + "\t" + list[idx] + "\n";
				}
			}
		}
		return str;
	}

	public int[] appendI(int[] list, int value) {
		int[] listCopy = new int[list.length + 1];
		int idx = 0;
		for (idx = 0; idx < list.length; idx++) {
			listCopy[idx] = list[idx];
		}
		listCopy[idx] = value;

		return listCopy;
	}

	public int[] insertI(int[] list, int i, int value) {
		int[] listCopy = new int[list.length + 1];
		int idx = 0;
		for (idx = 0; idx < list.length; idx++) {
			if (idx == i)
				listCopy[idx] = list[idx];
		}
		listCopy[idx] = value;

		return listCopy;
	}

	public int[] IArrayI(int mode, int[] list, int minMax, int min, int max, int eleAdd, String text, String text2) {
		int[] array = new int[list.length];
		int idx = 0;
		for (idx = 0; idx < array.length; idx++) {
			if (minMax == 0) {
				array[idx] = II(mode, text + (idx + eleAdd) + text2);
			}
			if (minMax == 10) {
				do {
					array[idx] = II(mode, text + (idx + eleAdd) + text2);
				} while (array[idx] < min);
			}
			if (minMax == 01) {
				do {
					array[idx] = II(mode, text + (idx + eleAdd) + text2);
				} while (array[idx] > max);
			}
			if (minMax == 11) {
				do {
					array[idx] = II(mode, text + (idx + eleAdd) + text2);
				} while (array[idx] < min && array[idx] > max);
			}
		}
		return array;
	}

	public double[] IArrayD(int mode, double[] list, int minMax, double min, double max, int eleAdd, String text,
			String text2) {
		double[] array = new double[list.length];
		int idx = 0;
		for (idx = 0; idx < array.length; idx++) {
			if (minMax == 0) {
				array[idx] = ID(mode, text + (idx + eleAdd) + text2);
			}
			if (minMax == 10) {
				do {
					array[idx] = ID(mode, text + (idx + eleAdd) + text2);
				} while (array[idx] < min);
			}
			if (minMax == 01) {
				do {
					array[idx] = ID(mode, text + (idx + eleAdd) + text2);
				} while (array[idx] > max);
			}
			if (minMax == 11) {
				do {
					array[idx] = ID(mode, text + (idx + eleAdd) + text2);
				} while (array[idx] < min && array[idx] > max);
			}
		}
		return array;
	}

	public int sumI(int[] list) {
		int idx = 0, sum = 0;
		for (idx = 0; idx < list.length; idx++) {
			sum += list[idx];
		}
		return sum;
	}

	public double sumD(double[] list) {
		int idx = 0;
		double sum = 0;
		for (idx = 0; idx < list.length; idx++) {
			sum += list[idx];
		}
		return sum;
	}

	public int leastI(int[] list) {
		int idx = 0;
		int value = list[0], element = 0;
		for (idx = 0; idx < list.length; idx++) {
			if (list[idx] < value) {
				element = idx;
				value = list[idx];
			}
		}
		return element;
	}

	public int leastD(double[] list) {
		int idx = 0;
		double value = list[0];
		int element = 0;
		for (idx = 0; idx < list.length; idx++) {
			if (list[idx] < value) {
				element = idx;
				value = list[idx];
			}
		}
		return element;
	}

	public int mostI(int[] list) {
		int idx = 0;
		int value = list[0], element = 0;
		for (idx = 0; idx < list.length; idx++) {
			if (list[idx] > value) {
				element = idx;
				value = list[idx];
			}
		}
		return element;
	}

	public int mostD(double[] list) {
		int idx = 0;
		double value = list[0];
		int element = 0;
		for (idx = 0; idx < list.length; idx++) {
			if (list[idx] > value) {
				element = idx;
				value = list[idx];
			}
		}
		return element;
	}

	public void EX() {
		System.exit(0);
	}
}