import "bootstrap/dist/css/bootstrap.css";
import './page.css';

export default function pageGenerate() {

    //const [box, setBox] = useState([]);

    // const newBox = newBox.map(() => {
    //     <div class="col border border-black rounded text-center border-3px" id="box1">
    //         <p class="my-auto">box1</p>
    //     </div>
    // })

    //Generates a box with filled info
    function generateBox(id) {
        return (
            <div class="col border border-black rounded text-center border-3px" id="box1">
                <p class="my-auto">box {id}</p>
            </div>
        )
    }

    //Generates a full row with boxes
    function generateRow() {

    }



    //Generates and returns the full page  
    // return (
    //     <div id="outerShell">
    //         <div id="topBar">
    //             <p>Top bar</p>
    //         </div>
    //         <div class="w-100" id="rowBreak"></div>
    //         <div class="container" id="contentBox">
    //             <div class="row justify-content-lg-center align-items-center" id="row1">
    //                 {generateBox}
    //                 {generateBox}
    //                 <div class="col border border-black rounded text-center border-3px" id="box3">
    //                     <p class="my-auto">box3</p>
    //                 </div>
    //             </div>
    //             <div class="w-100" id="rowBreak"></div>
    //             <div class="row justify-content-lg-center align-items-center" id="row2">
    //                 <div class="col border border-black rounded text-center border-3px" id="box1">
    //                     <p class="my-auto">box1</p>
    //                 </div>
    //                 <div class="col border border-black rounded text-center border-3px" id="box2">
    //                     <p class="my-auto">box2</p>
    //                 </div>
    //                 <div class="col border border-black rounded text-center border-3px" id="box3">
    //                     <p class="my-auto">box3</p>
    //                 </div>
    //             </div>
    //             <div class="w-100" id="rowBreak"></div>
    //             <div class="row justify-content-lg-center align-items-center" id="row3">
    //                 <div class="col border border-black rounded text-center border-3px" id="box1">
    //                     <p class="my-auto">box1</p>
    //                 </div>
    //                 <div class="col border border-black rounded text-center border-3px" id="box2">
    //                     <p class="my-auto">box2</p>
    //                 </div>
    //                 <div class="col border border-black rounded text-center border-3px" id="box3">
    //                     <p class="my-auto">box3</p>
    //                 </div>
    //             </div>
    //             {/* Can we revursivly generate rows? */}
    //         </div>
    //     </div>
    // )
}