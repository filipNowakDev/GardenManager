import React from 'react';
import { Link } from 'react-router-dom';

export const Navbar = () => {
    return (
        <nav>
            <div>
                <h1>Garden Manager</h1>

                <div className="navContent">
                    <Link to="/">Home</Link> |{" "}
                    <Link to="/devices">Devices</Link> |{" "}
                </div>
            </div>
        </nav>
    )
}