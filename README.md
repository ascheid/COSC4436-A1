# COSC4436-A1
COSC4436 - Computer Networks @ Algoma University | Assignment 1

Assignment description:

For any IP address entered, display the Network class, the default (classfull) subnet mask, and the CIDR notation.
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
