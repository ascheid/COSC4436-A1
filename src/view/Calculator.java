package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import model.Ip;
import model.IpAndSubnet;
import model.IpVLSM;

/* 
 * Code developed by Alvaro Scheid to resolve a problem from Computer Networks:
 * For any IP address entered, display the Network class, the default (classfull) subnet mask, and the CIDR notation.
Also display how many hosts per subnet, the network address and broadcast address.

For instance, if you enter 199.212.55.7

The result should be:

Network Class: C
Subnet Mask: 255.255.255.0
CIDR: /24
Hosts per subnet: 254
Network Address: 199.212.55.0
Broadcast Address: 199.212.55.255
Bits in Host: 8
Bits in Network: 24

You also need to be able to specify an IP address with a VLSM (variable length subnet mask) or an IP address with a dotted decimal notation subnet mask.

For instance if you enter:

199.212.55.7/16 or 199.212.55.7 255.255.0.0 the result should be

Subnet Mask: 255.255.0.0
CIDR: /16
Hosts per subnet: 65534
Network Address: 199.212.0.0
Broadcast Address: 199.212.255.255
Bits in Host: 16
Bits in Network: 16
 */
public class Calculator {

	
	/* Variable declaration for use */
	private JFrame frmSubnetCalculator;
	private JTextField firstOct;
	private JTextField secondOct;
	private JTextField thirdOct;
	private JTextField fourthOct;
	private Ip ip;
	private IpVLSM ipVLSM;
	private IpAndSubnet ipAndSubnet;
	private JTextField vlsm1Oct;
	private JTextField vlsm2Oct;
	private JTextField vlsm3Oct;
	private JTextField vlsm4Oct;
	private JTextField vlsmLength;
	private JTextField ipSubnet1Oct;
	private JTextField ipSubnet2Oct;
	private JTextField ipSubnet3Oct;
	private JTextField ipSubnet4Oct;
	private JTextField subnet1Oct;
	private JTextField subnet2Oct;
	private JTextField subnet3Oct;
	private JTextField subnet4Oct;
	private JTextField networkClass;
	private JTextField subnetMask;
	private JTextField cidr;
	private JTextField hostsPerSubnet;
	private JTextField networkAddress;
	private JTextField broadcastAddress;
	private JTextField bitsInHost;
	private JTextField bitsInNetwork;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					Calculator window = new Calculator();
					window.frmSubnetCalculator.setVisible(true);
					window.frmSubnetCalculator.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Calculator() {
		initialize();

	}

	private String replaceMissingChars(String bin) {
		if (bin.length() < 8) {
			String a = "";
			for (int i = 0; i < 8 - bin.length(); i++) {
				a = a + "0";
			}
			bin = a + bin;
		}
		return bin;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		
		/* CREATION OF ALL ITEMS FOR THE UI PANEL! */
		frmSubnetCalculator = new JFrame();
		frmSubnetCalculator.setResizable(false);
		frmSubnetCalculator.setLocationRelativeTo(null);
		frmSubnetCalculator.setTitle("Subnet Calculator");
		frmSubnetCalculator.setBounds(100, 100, 435, 457);
		frmSubnetCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSubnetCalculator.getContentPane().setLayout(null);
		ip = new Ip();
		ipVLSM = new IpVLSM();
		ipAndSubnet = new IpAndSubnet();

		JLabel lblNewLabel = new JLabel("Enter the ip adress:");
		lblNewLabel.setBounds(10, 11, 102, 14);
		frmSubnetCalculator.getContentPane().add(lblNewLabel);

		firstOct = new JTextField();
		firstOct.setHorizontalAlignment(SwingConstants.RIGHT);
		firstOct.setToolTipText("Value should be an integer 1-255");
		firstOct.setBounds(72, 36, 30, 20);
		frmSubnetCalculator.getContentPane().add(firstOct);
		firstOct.setColumns(3);

		secondOct = new JTextField();
		secondOct.setHorizontalAlignment(SwingConstants.RIGHT);
		secondOct.setToolTipText("Value should be an integer 1-255");
		secondOct.setBounds(132, 36, 30, 20);
		frmSubnetCalculator.getContentPane().add(secondOct);
		secondOct.setColumns(3);

		thirdOct = new JTextField();
		thirdOct.setHorizontalAlignment(SwingConstants.RIGHT);
		thirdOct.setToolTipText("Value should be an integer 1-255");
		thirdOct.setBounds(192, 36, 30, 20);
		frmSubnetCalculator.getContentPane().add(thirdOct);
		thirdOct.setColumns(3);

		fourthOct = new JTextField();
		fourthOct.setHorizontalAlignment(SwingConstants.RIGHT);
		fourthOct.setToolTipText("Value should be an integer 1-255");
		fourthOct.setBounds(252, 36, 30, 20);
		frmSubnetCalculator.getContentPane().add(fourthOct);
		fourthOct.setColumns(3);

		JLabel label = new JLabel(".");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(112, 39, 10, 14);
		frmSubnetCalculator.getContentPane().add(label);

		JLabel label_1 = new JLabel(".");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(172, 39, 10, 14);
		frmSubnetCalculator.getContentPane().add(label_1);

		JLabel label_2 = new JLabel(".");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(232, 39, 10, 14);
		frmSubnetCalculator.getContentPane().add(label_2);

		JButton btnNewButton = new JButton("Show Info");

		btnNewButton.setBounds(324, 158, 81, 22);
		frmSubnetCalculator.getContentPane().add(btnNewButton);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 191, 395, 2);
		frmSubnetCalculator.getContentPane().add(separator);

		JLabel lblIpI = new JLabel("Result");
		lblIpI.setBounds(10, 204, 39, 14);
		frmSubnetCalculator.getContentPane().add(lblIpI);

		JLabel lblNewLabel_1 = new JLabel("Network Class: ");
		lblNewLabel_1.setBounds(10, 229, 75, 14);
		frmSubnetCalculator.getContentPane().add(lblNewLabel_1);

		JLabel lblSubnetMask = new JLabel("Subnet Mask:");
		lblSubnetMask.setBounds(10, 254, 65, 14);
		frmSubnetCalculator.getContentPane().add(lblSubnetMask);

		JLabel lblCidr = new JLabel("CIDR: ");
		lblCidr.setBounds(10, 279, 32, 14);
		frmSubnetCalculator.getContentPane().add(lblCidr);

		JLabel lblHostPerSubnet = new JLabel("Host per Subnet: ");
		lblHostPerSubnet.setBounds(10, 304, 90, 14);
		frmSubnetCalculator.getContentPane().add(lblHostPerSubnet);

		JLabel lblNetworkAdress = new JLabel("Network Address:");
		lblNetworkAdress.setBounds(10, 329, 90, 14);
		frmSubnetCalculator.getContentPane().add(lblNetworkAdress);

		JLabel lblBroadcastAdress = new JLabel("Broadcast Adress:");
		lblBroadcastAdress.setBounds(10, 354, 90, 14);
		frmSubnetCalculator.getContentPane().add(lblBroadcastAdress);

		JLabel lblBitsInHost = new JLabel("Bits in Host:");
		lblBitsInHost.setBounds(10, 379, 57, 14);
		frmSubnetCalculator.getContentPane().add(lblBitsInHost);

		JLabel lblBitsInNetwork = new JLabel("Bits in Network:");
		lblBitsInNetwork.setBounds(10, 404, 75, 14);
		frmSubnetCalculator.getContentPane().add(lblBitsInNetwork);

		vlsm1Oct = new JTextField();
		vlsm1Oct.setEnabled(false);
		vlsm1Oct.setToolTipText("Value should be an integer 1-255");
		vlsm1Oct.setHorizontalAlignment(SwingConstants.RIGHT);
		vlsm1Oct.setColumns(3);
		vlsm1Oct.setBounds(72, 67, 30, 20);
		frmSubnetCalculator.getContentPane().add(vlsm1Oct);

		JLabel label_3 = new JLabel(".");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(112, 73, 10, 14);
		frmSubnetCalculator.getContentPane().add(label_3);

		vlsm2Oct = new JTextField();
		vlsm2Oct.setEnabled(false);
		vlsm2Oct.setToolTipText("Value should be an integer 1-255");
		vlsm2Oct.setHorizontalAlignment(SwingConstants.RIGHT);
		vlsm2Oct.setColumns(3);
		vlsm2Oct.setBounds(132, 67, 30, 20);
		frmSubnetCalculator.getContentPane().add(vlsm2Oct);

		vlsm3Oct = new JTextField();
		vlsm3Oct.setEnabled(false);
		vlsm3Oct.setToolTipText("Value should be an integer 1-255");
		vlsm3Oct.setHorizontalAlignment(SwingConstants.RIGHT);
		vlsm3Oct.setColumns(3);
		vlsm3Oct.setBounds(192, 67, 30, 20);
		frmSubnetCalculator.getContentPane().add(vlsm3Oct);

		vlsm4Oct = new JTextField();
		vlsm4Oct.setEnabled(false);
		vlsm4Oct.setToolTipText("Value should be an integer 1-255");
		vlsm4Oct.setHorizontalAlignment(SwingConstants.RIGHT);
		vlsm4Oct.setColumns(3);
		vlsm4Oct.setBounds(252, 67, 30, 20);
		frmSubnetCalculator.getContentPane().add(vlsm4Oct);

		JLabel label_4 = new JLabel(".");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(232, 73, 10, 14);
		frmSubnetCalculator.getContentPane().add(label_4);

		JLabel label_5 = new JLabel(".");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(172, 73, 10, 14);
		frmSubnetCalculator.getContentPane().add(label_5);

		JLabel label_6 = new JLabel("/");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(292, 70, 10, 14);
		frmSubnetCalculator.getContentPane().add(label_6);

		vlsmLength = new JTextField();
		vlsmLength.setEnabled(false);
		vlsmLength.setBounds(314, 67, 30, 20);
		frmSubnetCalculator.getContentPane().add(vlsmLength);
		vlsmLength.setColumns(10);

		ipSubnet1Oct = new JTextField();
		ipSubnet1Oct.setEnabled(false);
		ipSubnet1Oct.setToolTipText("Value should be an integer 1-255");
		ipSubnet1Oct.setHorizontalAlignment(SwingConstants.RIGHT);
		ipSubnet1Oct.setColumns(3);
		ipSubnet1Oct.setBounds(72, 98, 30, 20);
		frmSubnetCalculator.getContentPane().add(ipSubnet1Oct);

		ipSubnet2Oct = new JTextField();
		ipSubnet2Oct.setEnabled(false);
		ipSubnet2Oct.setToolTipText("Value should be an integer 1-255");
		ipSubnet2Oct.setHorizontalAlignment(SwingConstants.RIGHT);
		ipSubnet2Oct.setColumns(3);
		ipSubnet2Oct.setBounds(132, 97, 30, 20);
		frmSubnetCalculator.getContentPane().add(ipSubnet2Oct);

		ipSubnet3Oct = new JTextField();
		ipSubnet3Oct.setEnabled(false);
		ipSubnet3Oct.setToolTipText("Value should be an integer 1-255");
		ipSubnet3Oct.setHorizontalAlignment(SwingConstants.RIGHT);
		ipSubnet3Oct.setColumns(3);
		ipSubnet3Oct.setBounds(192, 97, 30, 20);
		frmSubnetCalculator.getContentPane().add(ipSubnet3Oct);

		ipSubnet4Oct = new JTextField();
		ipSubnet4Oct.setEnabled(false);
		ipSubnet4Oct.setToolTipText("Value should be an integer 1-255");
		ipSubnet4Oct.setHorizontalAlignment(SwingConstants.RIGHT);
		ipSubnet4Oct.setColumns(3);
		ipSubnet4Oct.setBounds(252, 98, 30, 20);
		frmSubnetCalculator.getContentPane().add(ipSubnet4Oct);

		subnet1Oct = new JTextField();
		subnet1Oct.setEnabled(false);
		subnet1Oct.setToolTipText("Value should be an integer 1-255");
		subnet1Oct.setHorizontalAlignment(SwingConstants.RIGHT);
		subnet1Oct.setColumns(3);
		subnet1Oct.setBounds(72, 129, 30, 20);
		frmSubnetCalculator.getContentPane().add(subnet1Oct);

		subnet2Oct = new JTextField();
		subnet2Oct.setEnabled(false);
		subnet2Oct.setToolTipText("Value should be an integer 1-255");
		subnet2Oct.setHorizontalAlignment(SwingConstants.RIGHT);
		subnet2Oct.setColumns(3);
		subnet2Oct.setBounds(132, 129, 30, 20);
		frmSubnetCalculator.getContentPane().add(subnet2Oct);

		subnet3Oct = new JTextField();
		subnet3Oct.setEnabled(false);
		subnet3Oct.setToolTipText("Value should be an integer 1-255");
		subnet3Oct.setHorizontalAlignment(SwingConstants.RIGHT);
		subnet3Oct.setColumns(3);
		subnet3Oct.setBounds(192, 128, 30, 20);
		frmSubnetCalculator.getContentPane().add(subnet3Oct);

		subnet4Oct = new JTextField();
		subnet4Oct.setEnabled(false);
		subnet4Oct.setToolTipText("Value should be an integer 1-255");
		subnet4Oct.setHorizontalAlignment(SwingConstants.RIGHT);
		subnet4Oct.setColumns(3);
		subnet4Oct.setBounds(252, 129, 30, 20);
		frmSubnetCalculator.getContentPane().add(subnet4Oct);

		JLabel label_7 = new JLabel(".");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(112, 103, 10, 14);
		frmSubnetCalculator.getContentPane().add(label_7);

		JLabel label_8 = new JLabel(".");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(112, 135, 10, 14);
		frmSubnetCalculator.getContentPane().add(label_8);

		JLabel label_9 = new JLabel(".");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(172, 135, 10, 14);
		frmSubnetCalculator.getContentPane().add(label_9);

		JLabel label_10 = new JLabel(".");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setBounds(172, 103, 10, 14);
		frmSubnetCalculator.getContentPane().add(label_10);

		JLabel label_11 = new JLabel(".");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setBounds(232, 103, 10, 14);
		frmSubnetCalculator.getContentPane().add(label_11);

		JLabel label_12 = new JLabel(".");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setBounds(232, 132, 10, 14);
		frmSubnetCalculator.getContentPane().add(label_12);

		JComboBox typeOfIp = new JComboBox();
		
		/*ListenerFunction that acts resolving the problem from the radio box selection
		 * When you click in a radio box, all the others need to bem turned of*/
		typeOfIp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (typeOfIp.getSelectedIndex() == 0) {
					System.out.println("Selecionado 0");
					firstOct.setEnabled(true);
					secondOct.setEnabled(true);
					thirdOct.setEnabled(true);
					fourthOct.setEnabled(true);
					vlsm1Oct.setEnabled(false);
					vlsm2Oct.setEnabled(false);
					vlsm3Oct.setEnabled(false);
					vlsm4Oct.setEnabled(false);
					vlsmLength.setEnabled(false);
					ipSubnet1Oct.setEnabled(false);
					ipSubnet2Oct.setEnabled(false);
					ipSubnet3Oct.setEnabled(false);
					ipSubnet4Oct.setEnabled(false);
					subnet1Oct.setEnabled(false);
					subnet2Oct.setEnabled(false);
					subnet3Oct.setEnabled(false);
					subnet4Oct.setEnabled(false);
				}
				if (typeOfIp.getSelectedIndex() == 1) {
					System.out.println("Selecionado 1");
					firstOct.setEnabled(false);
					secondOct.setEnabled(false);
					thirdOct.setEnabled(false);
					fourthOct.setEnabled(false);
					vlsm1Oct.setEnabled(true);
					vlsm2Oct.setEnabled(true);
					vlsm3Oct.setEnabled(true);
					vlsm4Oct.setEnabled(true);
					vlsmLength.setEnabled(true);
					ipSubnet1Oct.setEnabled(false);
					ipSubnet2Oct.setEnabled(false);
					ipSubnet3Oct.setEnabled(false);
					ipSubnet4Oct.setEnabled(false);
					subnet1Oct.setEnabled(false);
					subnet2Oct.setEnabled(false);
					subnet3Oct.setEnabled(false);
					subnet4Oct.setEnabled(false);
				}
				if (typeOfIp.getSelectedIndex() == 2) {
					System.out.println("Selecionado 2");
					firstOct.setEnabled(false);
					secondOct.setEnabled(false);
					thirdOct.setEnabled(false);
					fourthOct.setEnabled(false);
					vlsm1Oct.setEnabled(false);
					vlsm2Oct.setEnabled(false);
					vlsm3Oct.setEnabled(false);
					vlsm4Oct.setEnabled(false);
					vlsmLength.setEnabled(false);
					ipSubnet1Oct.setEnabled(true);
					ipSubnet2Oct.setEnabled(true);
					ipSubnet3Oct.setEnabled(true);
					ipSubnet4Oct.setEnabled(true);
					subnet1Oct.setEnabled(true);
					subnet2Oct.setEnabled(true);
					subnet3Oct.setEnabled(true);
					subnet4Oct.setEnabled(true);
				}
			}
		});
		typeOfIp.setModel(new DefaultComboBoxModel(new String[] { "Simple IP", "IP + VLSM", "IP + Subnet" }));
		typeOfIp.setSelectedIndex(0);
		typeOfIp.setBounds(112, 8, 170, 20);
		frmSubnetCalculator.getContentPane().add(typeOfIp);

		JButton button = new JButton("?");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frmSubnetCalculator,
						"Program to calculate and show the info of an ip\nAlvaro Scheid", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		button.setBounds(368, 7, 37, 23);
		frmSubnetCalculator.getContentPane().add(button);

		networkClass = new JTextField();
		networkClass.setEnabled(false);
		networkClass.setBounds(103, 226, 46, 20);
		frmSubnetCalculator.getContentPane().add(networkClass);
		networkClass.setColumns(10);

		subnetMask = new JTextField();
		subnetMask.setEnabled(false);
		subnetMask.setBounds(103, 251, 179, 20);
		frmSubnetCalculator.getContentPane().add(subnetMask);
		subnetMask.setColumns(10);

		cidr = new JTextField();
		cidr.setEnabled(false);
		cidr.setBounds(103, 276, 46, 20);
		frmSubnetCalculator.getContentPane().add(cidr);
		cidr.setColumns(10);

		hostsPerSubnet = new JTextField();
		hostsPerSubnet.setEnabled(false);
		hostsPerSubnet.setBounds(103, 301, 179, 20);
		frmSubnetCalculator.getContentPane().add(hostsPerSubnet);
		hostsPerSubnet.setColumns(10);

		networkAddress = new JTextField();
		networkAddress.setEnabled(false);
		networkAddress.setBounds(103, 326, 179, 20);
		frmSubnetCalculator.getContentPane().add(networkAddress);
		networkAddress.setColumns(10);

		broadcastAddress = new JTextField();
		broadcastAddress.setEnabled(false);
		broadcastAddress.setBounds(103, 351, 179, 20);
		frmSubnetCalculator.getContentPane().add(broadcastAddress);
		broadcastAddress.setColumns(10);

		bitsInHost = new JTextField();
		bitsInHost.setEnabled(false);
		bitsInHost.setBounds(103, 376, 179, 20);
		frmSubnetCalculator.getContentPane().add(bitsInHost);
		bitsInHost.setColumns(10);

		bitsInNetwork = new JTextField();
		bitsInNetwork.setEnabled(false);
		bitsInNetwork.setBounds(102, 401, 180, 20);
		frmSubnetCalculator.getContentPane().add(bitsInNetwork);
		bitsInNetwork.setColumns(10);
		
		/*
		 * Code of the function the takes care and handle all te calculations for the problem
		 * proposed for the assignment! There are three options that follow the selection from
		 * the radio box. Each one have diff aproach for the solution.
		 * 
		 * TYPE 0 == SIMPLE IP
		 * TYPE 1 == IP WITH THE CIDR BEING INFORMED
		 * TYPE 2 == IP WITH THE SUBMASK BEING INFORMED
		 * 
		 * */
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				/*
				 * When the user clicks for the option with a subnet being entered
				 * 
				 * */
				
				if (typeOfIp.getSelectedIndex() == 2) {
					
					/* Data gathered from the texts fields and stored in the class created for that option
					 * */
					
					
					ipAndSubnet.setFirstOctet(Integer.parseInt(ipSubnet1Oct.getText()));
					ipAndSubnet.setSecondOctet(Integer.parseInt(ipSubnet2Oct.getText()));
					ipAndSubnet.setThirdOctet(Integer.parseInt(ipSubnet3Oct.getText()));
					ipAndSubnet.setFourthOctet(Integer.parseInt(ipSubnet4Oct.getText()));
					ipAndSubnet.setFirstOctSubNet(Integer.parseInt(subnet1Oct.getText()));
					ipAndSubnet.setSecondOctSubNet(Integer.parseInt(subnet2Oct.getText()));
					ipAndSubnet.setThirdOctSubNet(Integer.parseInt(subnet3Oct.getText()));
					ipAndSubnet.setFourthOctSubNet(Integer.parseInt(subnet4Oct.getText()));
					
					
					/*Code that decides witch class is being entered*/
					
					if (ipAndSubnet.getFirstOctet() >= 1 && ipAndSubnet.getFirstOctet() <= 126)
						networkClass.setText("A");
					if (ipAndSubnet.getFirstOctet() >= 128 && ipAndSubnet.getFirstOctet() <= 191)
						networkClass.setText("B");
					if (ipAndSubnet.getFirstOctet() >= 192 && ipAndSubnet.getFirstOctet() <= 223)
						networkClass.setText("C");
					if (ipAndSubnet.getFirstOctet() >= 224 && ipAndSubnet.getFirstOctet() <= 239)
						networkClass.setText("D");
					if (ipAndSubnet.getFirstOctet() >= 240 && ipAndSubnet.getFirstOctet() <= 255)
						networkClass.setText("E");
					
					
					/*Showing the subnet mask the have already being entered by the user*/
					subnetMask.setText(ipAndSubnet.getFirstOctSubNet() + "." + 
							ipAndSubnet.getSecondOctSubNet() + "." + 
							ipAndSubnet.getThirdOctSubNet() + "." + ipAndSubnet.getFourthOctSubNet());
					
					
					
					/*Turning the subnet mask into four binary strings
					 * */
					
					String binNetworkAddr1 = Integer.toBinaryString(ipAndSubnet.getFirstOctSubNet());
					String binNetworkAddr2 = Integer.toBinaryString(ipAndSubnet.getSecondOctSubNet());
					String binNetworkAddr3 = Integer.toBinaryString(ipAndSubnet.getThirdOctSubNet());
					String binNetworkAddr4 = Integer.toBinaryString(ipAndSubnet.getFourthOctSubNet());
					
					/*All the binary strings being gathered together
					 * As the java toBinaryString Function doesnt shows the zeros, I made a function
					 * that replace the missings 0 on the left part of the binary number*/
					
					String binNetworkAddr = new String();
					binNetworkAddr = replaceMissingChars(binNetworkAddr1) + replaceMissingChars(binNetworkAddr2)
							+ replaceMissingChars(binNetworkAddr3) + replaceMissingChars(binNetworkAddr4);
					
					int counter = 0;
					
					/*
					 * Statement to count how many ones you have in the binary to count how many bits
					 * where taken from the host and network part
					 * 
					 * */
					
					for(int i=0;i<binNetworkAddr.length();i++) {
						if(binNetworkAddr.charAt(i) == '1')
							counter++;
					}
					
					bitsInNetwork.setText(Integer.toString(counter));
					bitsInHost.setText(Integer.toString(32 - counter));
					cidr.setText(Integer.toString(counter));
					
					double numberOfHosts = 0;
					numberOfHosts = Math.pow(2, 32-counter) - 2;
					System.out.println(counter);
					System.out.println(numberOfHosts);
					hostsPerSubnet.setText(Double.toString(numberOfHosts));
					
					int networkAddress1 = ipAndSubnet.getFirstOctSubNet() & ipAndSubnet.getFirstOctet();
					int networkAddress2 = ipAndSubnet.getSecondOctSubNet() & ipAndSubnet.getSecondOctet();
					int networkAddress3 = ipAndSubnet.getThirdOctSubNet() & ipAndSubnet.getThirdOctet();
					int networkAddress4 = ipAndSubnet.getFourthOctSubNet() & ipAndSubnet.getFourthOctet();
					
					networkAddress.setText(
							networkAddress1 + "." + networkAddress2 + "." + networkAddress3 + "." + networkAddress4);

			
					String binBroadcastAdd = binNetworkAddr.substring(0, counter);
					for(int i = 0 ; i<32-counter;i++)
						binBroadcastAdd += "1";
					System.out.println(binBroadcastAdd);
					
					String decNetworkAddr1 = binBroadcastAdd.substring(0, 8);
					String decNetworkAddr2 = binBroadcastAdd.substring(8, 16);
					String decNetworkAddr3 = binBroadcastAdd.substring(16, 24);
					String decNetworkAddr4 = binBroadcastAdd.substring(24, 32);
					
					int nA1 =  Integer.parseInt(decNetworkAddr1, 2);
					int nA2 =  Integer.parseInt(decNetworkAddr2, 2);
					int nA3 =  Integer.parseInt(decNetworkAddr3, 2);
					int nA4 =  Integer.parseInt(decNetworkAddr4, 2);
					
					broadcastAddress.setText(nA1 + "." + nA2 + "." + nA3 + "." + nA4);
				}
				// YOU GOT JUST THE VALUE OF VLSM
				if (typeOfIp.getSelectedIndex() == 1) {
					ipVLSM.setFirstOctet(Integer.parseInt(vlsm1Oct.getText()));
					ipVLSM.setSecondOctet(Integer.parseInt(vlsm2Oct.getText()));
					ipVLSM.setThirdOctet(Integer.parseInt(vlsm3Oct.getText()));
					ipVLSM.setFourthOctet(Integer.parseInt(vlsm4Oct.getText()));
					ipVLSM.setVlsm(Integer.parseInt(vlsmLength.getText()));

					cidr.setText(vlsmLength.getText());

					String maskBit = "";

					for (int i = 0; i < ipVLSM.getVlsm(); i++) {
						maskBit = maskBit + "1";
					}
					for (int i = 0; i < 32 - ipVLSM.getVlsm(); i++) {
						maskBit = maskBit + "0";
					}

					int counter = 0;
					for (int i = 0; i < maskBit.length(); i++)
						if (maskBit.charAt(i) == '1')
							counter++;

					bitsInNetwork.setText(Integer.toString(counter));
					bitsInHost.setText(Integer.toString(32 - counter));

					String primeiroOct = maskBit.substring(0, 8);
					String segundoOct = maskBit.substring(8, 16);
					String teceiroOct = maskBit.substring(16, 24);
					String quartoOct = maskBit.substring(24, 32);

					System.out.println(primeiroOct);
					System.out.println(segundoOct);
					System.out.println(teceiroOct);
					System.out.println(quartoOct);

					int primOct = Integer.parseInt(primeiroOct, 2);
					int secOct = Integer.parseInt(segundoOct, 2);
					int tercOct = Integer.parseInt(teceiroOct, 2);
					int quartOct = Integer.parseInt(quartoOct, 2);

					subnetMask.setText(primOct + "." + secOct + "." + tercOct + "." + quartOct);

					if (ipVLSM.getFirstOctet() >= 1 && ipVLSM.getFirstOctet() <= 126)
						networkClass.setText("A");
					if (ipVLSM.getFirstOctet() >= 128 && ipVLSM.getFirstOctet() <= 191)
						networkClass.setText("B");
					if (ipVLSM.getFirstOctet() >= 192 && ipVLSM.getFirstOctet() <= 223)
						networkClass.setText("C");
					if (ipVLSM.getFirstOctet() >= 224 && ipVLSM.getFirstOctet() <= 239)
						networkClass.setText("D");
					if (ipVLSM.getFirstOctet() >= 240 && ipVLSM.getFirstOctet() <= 255)
						networkClass.setText("E");

					int networkAddress1 = primOct & ipVLSM.getFirstOctet();
					int networkAddress2 = secOct & ipVLSM.getSecondOctet();
					int networkAddress3 = tercOct & ipVLSM.getThirdOctet();
					int networkAddress4 = quartOct & ipVLSM.getFourthOctet();

					networkAddress.setText(
							networkAddress1 + "." + networkAddress2 + "." + networkAddress3 + "." + networkAddress4);

					String binNetworkAddr1 = Integer.toBinaryString(networkAddress1);
					String binNetworkAddr2 = Integer.toBinaryString(networkAddress2);
					String binNetworkAddr3 = Integer.toBinaryString(networkAddress3);
					String binNetworkAddr4 = Integer.toBinaryString(networkAddress4);

					String binNetworkAddr = new String();
					binNetworkAddr = replaceMissingChars(binNetworkAddr1) + replaceMissingChars(binNetworkAddr2)
							+ replaceMissingChars(binNetworkAddr3) + replaceMissingChars(binNetworkAddr4);
					System.out.println();
					System.out.println(binNetworkAddr);
					
					String binBroadcastAdd = binNetworkAddr.substring(0, counter);
					for(int i = 0 ; i<32-counter;i++)
						binBroadcastAdd += "1";
					System.out.println(binBroadcastAdd);
					
					String decNetworkAddr1 = binBroadcastAdd.substring(0, 8);
					String decNetworkAddr2 = binBroadcastAdd.substring(8, 16);
					String decNetworkAddr3 = binBroadcastAdd.substring(16, 24);
					String decNetworkAddr4 = binBroadcastAdd.substring(24, 32);
					
					int nA1 =  Integer.parseInt(decNetworkAddr1, 2);
					int nA2 =  Integer.parseInt(decNetworkAddr2, 2);
					int nA3 =  Integer.parseInt(decNetworkAddr3, 2);
					int nA4 =  Integer.parseInt(decNetworkAddr4, 2);
					
					broadcastAddress.setText(nA1 + "." + nA2 + "." + nA3 + "." + nA4);
					double numberOfHosts = 0;
					numberOfHosts = Math.pow(2, 32-counter) - 2;
					System.out.println(counter);
					System.out.println(numberOfHosts);
					hostsPerSubnet.setText(Double.toString(numberOfHosts));
				}

				// ENTRY TYPE OF SIMPLE IP
				if (typeOfIp.getSelectedIndex() == 0) {

					String err = new String();
					boolean flag = false;

					if (firstOct.getText() == "") {
						err = err + "First octet without any value!";
						flag = true;
					}
					if (secondOct.getText() == "") {
						err = err + "Second octet without any value!";
						flag = true;
					}
					if (thirdOct.getText() == "") {
						err = err + "Third octet without any value!";
						flag = true;
					}
					if (fourthOct.getText() == "") {
						err = err + "Fourth octet without any value!";
						flag = true;
					}

					if (flag) {
						JOptionPane.showMessageDialog(frmSubnetCalculator, err, "Error!", JOptionPane.ERROR_MESSAGE);
					}

					System.out.println(flag);
					if (!flag) {

						ip.setFirstOctet(Integer.parseInt(firstOct.getText()));
						ip.setSecondOctet(Integer.parseInt(secondOct.getText()));
						ip.setThirdOctet(Integer.parseInt(thirdOct.getText()));
						ip.setFourthOctet(Integer.parseInt(fourthOct.getText()));

						if (ip.getFirstOctet() < 0 || ip.getFirstOctet() > 255) {
							err = err + " First octet is out of range! ";
							firstOct.setText("");
							flag = true;
						}
						if (ip.getSecondOctet() < 0 || ip.getSecondOctet() > 255) {
							secondOct.setText("");
							err = err + " Second octet is out of range! ";
							flag = true;
						}
						if (ip.getThirdOctet() < 0 || ip.getThirdOctet() > 255) {
							thirdOct.setText("");
							err = err + " Third octet is out of range! ";
							flag = true;
						}
						if (ip.getFourthOctet() < 0 || ip.getFourthOctet() > 255) {
							fourthOct.setText("");
							err = err + " Fourth octet is out of range! ";
							flag = true;
						}

						if (flag == true) {
							JOptionPane.showMessageDialog(frmSubnetCalculator, err, "Error!",
									JOptionPane.ERROR_MESSAGE);
						} else {
							System.out.println("Calcula demonho!");
							if (ip.getFirstOctet() >= 1 && ip.getFirstOctet() <= 126) {
								networkClass.setText("A");
								subnetMask.setText("255.0.0.0");
								bitsInHost.setText("24");
								bitsInNetwork.setText("8");
								cidr.setText("/ 8");
								networkAddress.setText(ip.getFirstOctet() + ".0.0.0");
								broadcastAddress.setText(ip.getFirstOctet() + ".255.255.255");
								hostsPerSubnet.setText("16777216");
							}
							if (ip.getFirstOctet() >= 128 && ip.getFirstOctet() <= 191 && ip.getSecondOctet() >= 0
									&& ip.getSecondOctet() <= 255) {
								networkClass.setText("B");
								subnetMask.setText("255.255.0.0");
								bitsInHost.setText("16");
								bitsInNetwork.setText("16");
								cidr.setText("/ 16");
								networkAddress.setText(ip.getFirstOctet() + "." + ip.getSecondOctet() + ".0.0");
								broadcastAddress.setText(ip.getFirstOctet() + "." + ip.getSecondOctet() + ".255.255");
								hostsPerSubnet.setText("65534");
							}
							if (ip.getFirstOctet() >= 192 && ip.getFirstOctet() <= 223) {
								networkClass.setText("C");
								subnetMask.setText("255.255.255.0");
								bitsInHost.setText("8");
								bitsInNetwork.setText("24");
								cidr.setText("/ 24");
								networkAddress.setText(ip.getFirstOctet() + "." + ip.getSecondOctet() + "."
										+ ip.getThirdOctet() + ".0");
								broadcastAddress.setText(ip.getFirstOctet() + "." + ip.getSecondOctet() + "."
										+ ip.getThirdOctet() + ".255");
								hostsPerSubnet.setText("254");
							}
							if (ip.getFirstOctet() >= 224 && ip.getFirstOctet() <= 239) {
								networkClass.setText("D - RESERVED");
								subnetMask.setText("N/A");
								bitsInHost.setText("N/A");
								bitsInNetwork.setText("N/A");
								cidr.setText("N/A");
								networkAddress.setText("N/A");
								broadcastAddress.setText("N/A");
								hostsPerSubnet.setText("N/A");
							}
							if (ip.getFirstOctet() >= 240 && ip.getFirstOctet() <= 255) {
								networkClass.setText("E - RESERVED");
								subnetMask.setText("N/A");
								bitsInHost.setText("N/A");
								bitsInNetwork.setText("N/A");
								cidr.setText("N/A");
								networkAddress.setText("N/A");
								broadcastAddress.setText("N/A");
								hostsPerSubnet.setText("N/A");
							}
						}
					}
				}
			}
		});
	}
}
