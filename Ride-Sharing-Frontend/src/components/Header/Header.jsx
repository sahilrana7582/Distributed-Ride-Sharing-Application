import React, { useState, useEffect } from 'react';
import { 
  AppBar, 
  Toolbar, 
  Typography, 
  Button, 
  Container, 
  Box, 
  IconButton, 
  Drawer, 
  List, 
  ListItem, 
  ListItemText,
  useScrollTrigger,
  Avatar
} from '@mui/material';
import { Link, useLocation } from 'react-router-dom';
import { Menu as MenuIcon, DirectionsCar } from '@mui/icons-material';
import './Header.scss';

const Header = () => {
  const [drawerOpen, setDrawerOpen] = useState(false);
  const [isScrolled, setIsScrolled] = useState(false);
  const location = useLocation();
  
  const trigger = useScrollTrigger({
    disableHysteresis: true,
    threshold: 50,
  });

  useEffect(() => {
    const handleScroll = () => {
      setIsScrolled(window.scrollY > 50);
    };
    
    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  const toggleDrawer = (open) => (event) => {
    if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
      return;
    }
    setDrawerOpen(open);
  };

  const navItems = [
    { name: 'Home', path: '/' },
    { name: 'Find Rides', path: '/map' },
    { name: 'Services', path: '/services' },
    { name: 'About', path: '/about' },
    { name: 'Contact', path: '/contact' },
  ];

  const isActive = (path) => {
    return location.pathname === path;
  };

  return (
    <AppBar 
      position="fixed" 
      className={`app-header ${isScrolled || trigger ? 'scrolled' : ''}`}
      elevation={isScrolled || trigger ? 4 : 0}
    >
      <Container maxWidth="lg">
        <Toolbar disableGutters>
          <Box className="logo-container">
            <DirectionsCar className="logo-icon" />
            <Typography
              variant="h6"
              component={Link}
              to="/"
              className="logo-text"
            >
              RideShare
            </Typography>
          </Box>

          <Box className="desktop-nav">
            {navItems.map((item) => (
              <Button
                key={item.name}
                component={Link}
                to={item.path}
                className={`nav-link ${isActive(item.path) ? 'active' : ''}`}
              >
                {item.name}
              </Button>
            ))}
          </Box>

          <Box className="auth-buttons desktop-only">
            <Button 
              variant="outlined" 
              color="primary" 
              className="login-button"
              component={Link}
              to="/login"
            >
              Login
            </Button>
            <Button 
              variant="contained" 
              color="primary" 
              className="signup-button"
              component={Link}
              to="/signup"
            >
              Sign Up
            </Button>
          </Box>

          <IconButton
            color="inherit"
            aria-label="open drawer"
            edge="start"
            onClick={toggleDrawer(true)}
            className="mobile-menu-button"
          >
            <MenuIcon />
          </IconButton>
        </Toolbar>
      </Container>

      <Drawer
        anchor="right"
        open={drawerOpen}
        onClose={toggleDrawer(false)}
        className="mobile-drawer"
      >
        <Box
          role="presentation"
          onClick={toggleDrawer(false)}
          onKeyDown={toggleDrawer(false)}
          className="drawer-content"
        >
          <Box className="drawer-header">
            <DirectionsCar className="drawer-logo-icon" />
            <Typography variant="h6" className="drawer-logo-text">
              RideShare
            </Typography>
          </Box>
          
          <List>
            {navItems.map((item) => (
              <ListItem 
                button 
                key={item.name} 
                component={Link} 
                to={item.path}
                className={isActive(item.path) ? 'active' : ''}
              >
                <ListItemText primary={item.name} />
              </ListItem>
            ))}
          </List>
          
          <Box className="drawer-auth-buttons">
            <Button 
              variant="outlined" 
              color="primary" 
              fullWidth 
              className="login-button"
              component={Link}
              to="/login"
            >
              Login
            </Button>
            <Button 
              variant="contained" 
              color="primary" 
              fullWidth 
              className="signup-button"
              component={Link}
              to="/signup"
            >
              Sign Up
            </Button>
          </Box>
        </Box>
      </Drawer>
    </AppBar>
  );
};

export default Header;