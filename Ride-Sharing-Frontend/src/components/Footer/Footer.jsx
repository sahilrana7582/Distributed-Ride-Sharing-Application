import React from 'react';
import { 
  Container, 
  Grid, 
  Typography, 
  Box, 
  Link, 
  IconButton, 
  Divider,
  Button
} from '@mui/material';
import { 
  Facebook, 
  Twitter, 
  Instagram, 
  LinkedIn, 
  DirectionsCar,
  Email,
  Phone,
  LocationOn
} from '@mui/icons-material';
import { Link as RouterLink } from 'react-router-dom';
import './Footer.scss';

const Footer = () => {
  return (
    <footer className="app-footer">
      <Container maxWidth="lg">
        <Grid container spacing={4}>
          <Grid item xs={12} md={4}>
            <Box className="footer-brand">
              <Box className="footer-logo">
                <DirectionsCar className="footer-logo-icon" />
                <Typography variant="h6" className="footer-logo-text">
                  RideShare
                </Typography>
              </Box>
              <Typography variant="body2" className="footer-description">
                Providing safe, reliable and affordable rides for everyone. Our mission is to make transportation more accessible and convenient.
              </Typography>
              <Box className="footer-social">
                <IconButton color="primary" aria-label="facebook">
                  <Facebook />
                </IconButton>
                <IconButton color="primary" aria-label="twitter">
                  <Twitter />
                </IconButton>
                <IconButton color="primary" aria-label="instagram">
                  <Instagram />
                </IconButton>
                <IconButton color="primary" aria-label="linkedin">
                  <LinkedIn />
                </IconButton>
              </Box>
            </Box>
          </Grid>
          
          <Grid item xs={12} sm={6} md={2}>
            <Typography variant="h6" className="footer-heading">
              Company
            </Typography>
            <ul className="footer-links">
              <li>
                <Link component={RouterLink} to="/about">About Us</Link>
              </li>
              <li>
                <Link component={RouterLink} to="/careers">Careers</Link>
              </li>
              <li>
                <Link component={RouterLink} to="/blog">Blog</Link>
              </li>
              <li>
                <Link component={RouterLink} to="/press">Press</Link>
              </li>
            </ul>
          </Grid>
          
          <Grid item xs={12} sm={6} md={2}>
            <Typography variant="h6" className="footer-heading">
              Services
            </Typography>
            <ul className="footer-links">
              <li>
                <Link component={RouterLink} to="/rides">Ride</Link>
              </li>
              <li>
                <Link component={RouterLink} to="/drive">Drive</Link>
              </li>
              <li>
                <Link component={RouterLink} to="/business">Business</Link>
              </li>
              <li>
                <Link component={RouterLink} to="/cities">Cities</Link>
              </li>
            </ul>
          </Grid>
          
          <Grid item xs={12} sm={6} md={2}>
            <Typography variant="h6" className="footer-heading">
              Support
            </Typography>
            <ul className="footer-links">
              <li>
                <Link component={RouterLink} to="/help">Help Center</Link>
              </li>
              <li>
                <Link component={RouterLink} to="/safety">Safety</Link>
              </li>
              <li>
                <Link component={RouterLink} to="/terms">Terms</Link>
              </li>
              <li>
                <Link component={RouterLink} to="/privacy">Privacy</Link>
              </li>
            </ul>
          </Grid>
          
          <Grid item xs={12} sm={6} md={2}>
            <Typography variant="h6" className="footer-heading">
              Contact
            </Typography>
            <ul className="footer-contact">
              <li>
                <Phone fontSize="small" />
                <span>+1 (555) 123-4567</span>
              </li>
              <li>
                <Email fontSize="small" />
                <span>support@rideshare.com</span>
              </li>
              <li>
                <LocationOn fontSize="small" />
                <span>123 Ride Street, San Francisco, CA 94103</span>
              </li>
            </ul>
          </Grid>
        </Grid>
        
        <Box className="footer-newsletter">
          <Typography variant="h6">
            Subscribe to our newsletter
          </Typography>
          <Typography variant="body2">
            Get the latest news and updates from RideShare
          </Typography>
          <Box className="newsletter-form">
            <input type="email" placeholder="Your email address" />
            <Button variant="contained" color="primary">
              Subscribe
            </Button>
          </Box>
        </Box>
        
        <Divider className="footer-divider" />
        
        <Box className="footer-bottom">
          <Typography variant="body2" className="copyright">
            © {new Date().getFullYear()} RideShare. All rights reserved.
          </Typography>
          <Box className="footer-bottom-links">
            <Link component={RouterLink} to="/terms">Terms</Link>
            <Link component={RouterLink} to="/privacy">Privacy</Link>
            <Link component={RouterLink} to="/cookies">Cookies</Link>
          </Box>
        </Box>
      </Container>
    </footer>
  );
};

export default Footer;