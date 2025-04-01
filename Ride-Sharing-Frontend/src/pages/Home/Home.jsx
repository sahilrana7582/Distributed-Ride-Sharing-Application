import React, { useState } from 'react';
import { motion } from 'framer-motion';
import { Button, Typography, Box, Container, Grid, Paper } from '@mui/material';
import { ArrowForward, LocationOn, AccessTime, Security } from '@mui/icons-material';
import './Home.scss';

const Home = () => {
  const [isHovered, setIsHovered] = useState(false);

  return (
    <div className="home-page">
      {/* Hero Section */}
      <section className="hero-section">
        <Container maxWidth="lg" style={{ overflow: 'hidden', padding: '0 1rem' }}>
          <Grid container spacing={4} alignItems="center">
            <Grid item xs={12} md={6}>
              <motion.div
                initial={{ opacity: 0, x: -50 }}
                animate={{ opacity: 1, x: 0 }}
                transition={{ duration: 0.8 }}
              >
                <Typography variant="h2" component="h1" className="hero-title">
                  Your Ride, <span className="highlight">Your Way</span>
                </Typography>
                <Typography variant="h5" component="p" className="hero-subtitle">
                  Fast, reliable rides at your fingertips. Anywhere, anytime.
                </Typography>
                <Box mt={4} display="flex" gap={2}>
                  <motion.div
                    whileHover={{ scale: 1.05 }}
                    whileTap={{ scale: 0.95 }}
                  >
                    <Button 
                      variant="contained" 
                      size="large" 
                      color="primary" 
                      endIcon={<ArrowForward />}
                      className="cta-button"
                    >
                      Book a Ride
                    </Button>
                  </motion.div>
                  <motion.div
                    whileHover={{ scale: 1.05 }}
                    whileTap={{ scale: 0.95 }}
                  >
                    <Button 
                      variant="outlined" 
                      size="large" 
                      color="primary"
                      className="secondary-button"
                    >
                      Become a Driver
                    </Button>
                  </motion.div>
                </Box>
              </motion.div>
            </Grid>
            <Grid item xs={12} md={6}>
              <motion.div
                initial={{ opacity: 0, y: 50 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ duration: 0.8, delay: 0.2 }}
                className="hero-image-container"
              >
                <img 
                  src="https://img.freepik.com/free-photo/luxurious-car-parked-highway-with-illuminated-headlight-sunset_181624-60607.jpg?t=st=1743438497~exp=1743442097~hmac=37675e73ce7ca99eb78a68f0c521e575dc5a247b75806f3192796df5a658d805&w=2000" 
                  alt="Ride sharing illustration" 
                  className="hero-image"
                />
              </motion.div>
            </Grid>
          </Grid>
        </Container>
      </section>

      {/* Features Section */}
      <section className="features-section">
        <Container maxWidth="lg">
          <motion.div
            initial={{ opacity: 0, y: 30 }}
            whileInView={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.6 }}
            viewport={{ once: true }}
          >
            <Typography variant="h3" component="h2" className="section-title" align="center">
              Why Choose Us
            </Typography>
          </motion.div>
          
          <Grid container spacing={4} mt={4}>
            {[
              { 
                icon: <LocationOn fontSize="large" />, 
                title: "Go Anywhere", 
                description: "Rides available all across the city, even in areas where others don't go." 
              },
              { 
                icon: <AccessTime fontSize="large" />, 
                title: "Quick Pickups", 
                description: "Our drivers arrive within minutes of your booking." 
              },
              { 
                icon: <Security fontSize="large" />, 
                title: "Safe & Secure", 
                description: "All rides are tracked and drivers are thoroughly vetted." 
              }
            ].map((feature, index) => (
              <Grid item xs={12} md={4} key={index}>
                <motion.div
                  initial={{ opacity: 0, y: 30 }}
                  whileInView={{ opacity: 1, y: 0 }}
                  transition={{ duration: 0.6, delay: index * 0.2 }}
                  viewport={{ once: true }}
                >
                  <Paper elevation={3} className="feature-card">
                    <div className="feature-icon">{feature.icon}</div>
                    <Typography variant="h5" component="h3" className="feature-title">
                      {feature.title}
                    </Typography>
                    <Typography variant="body1" className="feature-description">
                      {feature.description}
                    </Typography>
                  </Paper>
                </motion.div>
              </Grid>
            ))}
          </Grid>
        </Container>
      </section>

      {/* How It Works Section */}
      <section className="how-it-works-section">
        <Container maxWidth="lg">
          <motion.div
            initial={{ opacity: 0, y: 30 }}
            whileInView={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.6 }}
            viewport={{ once: true }}
          >
            <Typography variant="h3" component="h2" className="section-title" align="center">
              How It Works
            </Typography>
          </motion.div>
          
          <Box className="steps-container" mt={6}>
            {[
              { number: "01", title: "Request", description: "Enter your pickup location and destination" },
              { number: "02", title: "Match", description: "We'll match you with the nearest available driver" },
              { number: "03", title: "Ride", description: "Track your driver in real-time as they approach" },
              { number: "04", title: "Arrive", description: "Arrive at your destination safely and on time" }
            ].map((step, index) => (
              <motion.div
                key={index}
                className="step-item"
                initial={{ opacity: 0, y: 20 }}
                whileInView={{ opacity: 1, y: 0 }}
                transition={{ duration: 0.5, delay: index * 0.15 }}
                viewport={{ once: true }}
              >
                <div className="step-number">{step.number}</div>
                <Typography variant="h5" component="h3" className="step-title">
                  {step.title}
                </Typography>
                <Typography variant="body1" className="step-description">
                  {step.description}
                </Typography>
              </motion.div>
            ))}
          </Box>
        </Container>
      </section>

      {/* CTA Section */}
      <section className="cta-section">
        <Container maxWidth="md">
          <motion.div
            className="cta-container"
            initial={{ opacity: 0, scale: 0.95 }}
            whileInView={{ opacity: 1, scale: 1 }}
            transition={{ duration: 0.6 }}
            viewport={{ once: true }}
            onHoverStart={() => setIsHovered(true)}
            onHoverEnd={() => setIsHovered(false)}
          >
            <Typography variant="h3" component="h2" className="cta-title">
              Ready to get started?
            </Typography>
            <Typography variant="h6" component="p" className="cta-subtitle">
              Download our app now and experience the future of transportation. Enjoy seamless rides with just a few taps.
            </Typography>
            <Box mt={4} display="flex" justifyContent="center" gap={2} flexWrap="wrap">
              <motion.div
                whileHover={{ scale: 1.05 }}
                whileTap={{ scale: 0.95 }}
              >
                <Button 
                  variant="contained" 
                  size="large" 
                  color="primary" 
                  className="download-button"
                  startIcon={<svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M17.5227 7.39601V8.92935C17.5227 9.31491 17.2468 9.6016 16.8685 9.6016C16.4902 9.6016 16.2142 9.31491 16.2142 8.92935V7.39601C16.2142 6.16166 15.2322 5.16229 14.0161 5.16229H5.41481C4.19865 5.16229 3.21674 6.16166 3.21674 7.39601V16.6017C3.21674 17.8361 4.19865 18.8354 5.41481 18.8354H14.0161C15.2322 18.8354 16.2142 17.8361 16.2142 16.6017V15.0684C16.2142 14.6828 16.4902 14.3961 16.8685 14.3961C17.2468 14.3961 17.5227 14.6828 17.5227 15.0684V16.6017C17.5227 18.5542 15.9397 20.1641 14.0161 20.1641H5.41481C3.49117 20.1641 1.90825 18.5542 1.90825 16.6017V7.39601C1.90825 5.44351 3.49117 3.83366 5.41481 3.83366H14.0161C15.9397 3.83366 17.5227 5.44351 17.5227 7.39601Z" fill="white"/>
                    <path d="M20.4317 10.3933L17.8978 7.82274C17.6324 7.55275 17.1986 7.55275 16.9332 7.82274C16.6678 8.09272 16.6678 8.53367 16.9332 8.80365L18.5469 10.4389H10.5791C10.2008 10.4389 9.9248 10.7256 9.9248 11.1111C9.9248 11.4967 10.2008 11.7834 10.5791 11.7834H18.5469L16.9332 13.4186C16.6678 13.6886 16.6678 14.1295 16.9332 14.3995C17.0659 14.5345 17.2396 14.602 17.4133 14.602C17.587 14.602 17.7607 14.5345 17.8934 14.3995L20.4273 11.8289C20.6927 11.559 20.6927 11.118 20.4273 10.848C20.4273 10.8289 20.4317 10.8097 20.4317 10.3933Z" fill="white"/>
                  </svg>}
                >
                  App Store
                </Button>
              </motion.div>
              <motion.div
                whileHover={{ scale: 1.05 }}
                whileTap={{ scale: 0.95 }}
              >
                <Button 
                  variant="contained" 
                  size="large" 
                  color="primary" 
                  className="download-button"
                  startIcon={<svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M12.7137 11.9998L8.25488 7.54102L9.66888 6.12702L15.5417 11.9998L9.66888 17.8726L8.25488 16.4586L12.7137 11.9998Z" fill="white"/>
                  </svg>}
                >
                  Google Play
                </Button>
              </motion.div>
            </Box>
          </motion.div>
        </Container>
      </section>
    </div>
  );
};

export default Home;