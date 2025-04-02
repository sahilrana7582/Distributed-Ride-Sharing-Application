import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home/Home';
import Map from './pages/Map/Map';
import Header from './components/Header/Header';
import Footer from './components/Footer/Footer';
import './App.css';
import { SnackbarProvider } from 'notistack';

function App() {
  return (
    <SnackbarProvider maxSnack={3}>
      <Router>
        <div className="app">
          <Header />
          <main>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/map" element={<Map />} />
              {/* Add more routes as needed */}
            </Routes>
          </main>
          <Footer />
        </div>
      </Router>
    </SnackbarProvider>
  );
}

export default App;
