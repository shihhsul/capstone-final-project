import Nav from './Nav';

const Layout = (props) => {
    return (
        <div className='bg-blue-950 min-h-screen'>
            <Nav/>
            <main className='m-12 w-11/12 max-1-2xl' >
                {props.children}
            </main>
        </div>
    )
}
export default Layout