/**
 * Created by fangjiejie on 2017/8/6.
 */
var m1={
    name:"menu1",
    target:"#",
    child:[ {name:"menu1_1", target:"page3", child:null},
            {name:"menu1_2",target:"#",child:[{name:"menu1_2_1",target:"page4", child:null},
                                                 {name:"menu1_2_2",target:"page2",child:null},
                                                 {name:"menu1_2_3",target:"page1",child:null},
                                                 {name:"menu1_2_4",target:"page3",child:null}]
            },
            {name:"menu1_3",target:"page3",child:null},
            {name:"menu1_4",target:"page2",child:null}]
}
var m2={
    name:"menu2",
    target:"#",
    child:[{name:"menu2_1", target:"page3", child:null},
            {name:"menu2_2",target:"page4",child:null},
            {name:"menu2_3",target:"page1",child:null},
            {name:"menu2_4",target:"page2",child:null}]
}
var m3={
    name:"menu3",
    target:"#",
    child:[ {name:"menu3_1", target:"page4",child:null},
            {name:"menu3_2",target:"#",child:[{name:"menu3_2_1",target:"page3", child:null},
                                                {name:"menu3_2_2",target:"page1",child:null},
                                                {name:"menu3_2_3",target:"page2",child:null},
                                                {name:"menu3_2_4",target:"page4",child:null}]
            },
            {name:"menu3_3",target:"page4",child:null},
            {name:"menu3_4",target:"#",child:[{name:"menu3_4_1",target:"page4", child:null},
                                                {name:"menu3_4_2",target:"page1",child:null},
                                                {name:"menu3_4_3",target:"page2",child:null},
                                                {name:"menu3_4_4",target:"page3",child:null}]
            }]
};
var m4={
    name:"menu4",
    target:"#",
    child:[{name:"menu4_1", target:"page1",child:null},
            {name:"menu4_2",target:"page2",child:null},
            {name:"menu4_3",target:"page3",child:null},
            {name:"menu4_4",target:"page4",child:null}]
};
var m5={
    name:"menu5",
    target:"#",
    child:[{name:"menu5_1",target:"page1",child:null},
            {name:"menu5_2",target:"#",child:[{name:"menu5_2_1",target:"page1",child:null},
                                                {name:"menu5_2_2",target:"page2",child:null},
                                                {name:"menu5_2_3",target:"page3",child:null},
                                                {name:"menu5_2_4",target:"page4",child:null}]
        },
            {name:"menu5_3",target:"page3",child:null},
            {name:"menu5_4",target:"#",child:[{name:"menu5_4_1", target:"page1",child:null},
                                                {name:"menu5_4_2",target:"page4",child:null},
                                                {name:"menu5_4_3",target:"page3",child:null},
                                                {name:"menu5_4_4",target:"page2",child:null}]
        }]
};
var menu=[m1,m2,m3,m4,m5];