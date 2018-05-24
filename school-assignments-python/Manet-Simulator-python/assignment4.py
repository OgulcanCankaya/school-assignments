import sys
from math import sqrt as sq
command_file=open("commands3", "r")
command_list=[]
packet_size=int(sys.argv[1])
data_size=0
for i in command_file.readlines():
    temp=(i.strip('\n').split('\t'))
    if temp[1]=='SEND':
        data_size=int(temp[4])
    command_list.append(temp)

total_time=(data_size//packet_size)
nodes = []
coor={}
seperated_ranges={}
east_ranges={}
west_ranges={}
north_ranges={}
south_ranges={}
neighbor={}
battery={}
routes={}
def neighbor_find(node):
    neighbors=[]
    for i in range(len(nodes)):
        if int(west_ranges[node])<=int(coor[nodes[i]][0])<=int(east_ranges[node]) and int(south_ranges[node])<=int(coor[nodes[i]][1])<=int(north_ranges[node]) and node!=nodes[i]:
            neighbors.append(nodes[i])
    return(neighbors)
def cost(node1,node2):
    a=coor[node1]
    b=coor[node2]
    x_axis=abs(int(a[0])-int(b[0]))
    y_axis=abs(int(a[1])-int(b[1]))
    return sq((x_axis**2)+(y_axis**2))/int(battery[node2])
def find_paths(neighbor,node1,node2,path=[]):
    path = path +[node1]
    if node1==node2:
        return [path]
    if not neighbor.fromkeys(node1):
        return []
    paths=[]
    for node in neighbor[node1]:
        if node not in path:
            newpaths=find_paths(neighbor,node,node2,path)
            for newpath in newpaths:
                paths.append(newpath)
    return (paths)
def cost_of_routes(neighbor,node1,node2):
    new_list=find_paths(neighbor,node1,node2,path=[])
    costi=[]
    for i in range(len(new_list)):
        cost_of_route=0
        for a in range(len(new_list[i])-1):
            cost_of_route+=cost(new_list[i][a],new_list[i][a+1])
        costi.append(cost_of_route)
    return costi
costs=[]
cost_dic={}
cost_route_dic={}
is_there_a_path=0
from_point=None
to_point=None
print('********************************')
print('AD-HOC NETWORK SIMULATOR - BEGIN')
print('********************************')
for i in range(total_time+1):
    hours=i//3600
    minutes=i//60
    seconds=i%60
    print('SIMULATION TIME: '"%02d:%02d:%02d"%(hours,minutes,seconds))
    for a in range(len(command_list)):
        if int(command_list[a][0])==i and command_list[a][1]=='CRNODE':
            nodes.append(command_list[a][2])
            print("\tCOMMAND *CRNODE*: New node " +command_list[a][2] + " is created")
    for a in range(len(command_list)):
        if i!=0 and command_list[a][0]==i and command_list[a][1]=='CRNODE':
            is_there_a_path=0
    for a in range(len(command_list)):
        if int(command_list[a][0])==i and command_list[a][1]=='CRNODE':
            patates=command_list[a][3].split(';')
            coor.update({command_list[a][2]:patates})
            battery.update({command_list[a][2]: command_list[a][5]})
            banana=command_list[a][4].split(';')
            seperated_ranges.update({command_list[a][2]:banana})
    for a in range(len(command_list)):
        if int(command_list[a][0]) == i and command_list[a][1] == 'CRNODE':
            node=command_list[a][2]
            south_ranges.update({node:int(coor[node][1])-int(seperated_ranges[node][3])})
            north_ranges.update({node: int(coor[node][1]) + int(seperated_ranges[node][2])})
            west_ranges.update({node: int(coor[node][0]) - int(seperated_ranges[node][1])})
            east_ranges.update({node: int(seperated_ranges[node][0]) + int(coor[node][0])})
    for a in range(len(command_list)):
        if int(command_list[a][0])==i and command_list[a][1]=='CRNODE':
            banana=command_list[a][2]
            neighbor.update({banana:neighbor_find(banana)})
    for a in range(len(command_list)):
        if int(command_list[a][0])==i and command_list[a][1]=='SEND':
            is_there_a_path=0
            from_point=command_list[a][2]
            to_point=command_list[a][3]
            print("\tCOMMAND *SEND*: Data is ready to send from " +from_point + " to " +to_point)
    for a in range(len(command_list)):
        if int(command_list[a][0]) == i and command_list[a][1] == 'RMNODE':
            is_there_a_path=0
            nodes.remove(command_list[a][2])
            coor.pop(command_list[a][2])
            east_ranges.pop(command_list[a][2])
            west_ranges.pop(command_list[a][2])
            north_ranges.pop(command_list[a][2])
            south_ranges.pop(command_list[a][2])
            neighbor.pop(command_list[a][2])
            neighbor.update({nodes[b]:neighbor_find(nodes[b]) for b in range(len(nodes))})
            print("\tCOMMAND *RMNODE*: Node " +command_list[a][2] + " is removed")
    for a in range(len(command_list)):
        if int(command_list[a][0]) == i and command_list[a][1] == 'MOVE':
            is_there_a_path=0
            print("\tCOMMAND *MOVE*: The location of node " +command_list[a][2] + " is changed")
            potato=command_list[a][3].split(';')
            coor.update({command_list[a][2]:potato})
            node=command_list[a][2]
            south_ranges.update({node:int(coor[node][1])-int(seperated_ranges[node][3])})
            north_ranges.update({node: int(coor[node][1]) + int(seperated_ranges[node][2])})
            west_ranges.update({node: int(coor[node][0]) - int(seperated_ranges[node][1])})
            east_ranges.update({node: int(seperated_ranges[node][0]) + int(coor[node][0])})
            neighbor.update({nodes[b]: neighbor_find(nodes[b]) for b in range(len(nodes))})
    for a in range(len(command_list)):
        if int(command_list[a][0]) == i and command_list[a][1] == 'CHBTTRY':
            battery.update({command_list[a][2]:int(command_list[a][3])})
            print("\tCOMMAND *CHBTTRY*: Battery level of node " + command_list[a][2] + " is changed to " +command_list[a][3])
            is_there_a_path=0
    if is_there_a_path==0:
        print('\tNODES & THEIR NEIGHBORS:', end=" ")
        for a in range(len(nodes)):
            print(nodes[a],"->",", ".join(neighbor[nodes[a]]),end=" |")
        paths=find_paths(neighbor,from_point,to_point)
        if len(paths)==0:
            print("\n","NO ROUTE FROM", from_point,"TO",to_point,"FOUND")
            break
        costs=cost_of_routes(neighbor,from_point,to_point)
        print("\n\t",len(paths),"ROUTE(S) FOUND:" )
        for f in range(len(paths)):
            routes.update({f+1:paths[f]})
        for j in range(len(paths)):
            print("\tROUTE",j+1,":"," -> ".join(routes[j+1]),"\t COST: ",round(costs[j],4))
            cost_dic.update({j+1:round(costs[j],4)})
            cost_route_dic.update({round(costs[j],4):j+1})
        equal_cost=[]
        for j in range(len(costs)):
            if costs[j]==min(costs):
                equal_cost.append(j)
        chooosen=cost_route_dic[round(min(costs),4)]
        len_equal=[]
        if len(equal_cost)>1:
            for e in range(len(equal_cost)):
                len_equal.append(len(paths[equal_cost[e]]))
                len_equal.append(paths[equal_cost[e]])
                len_equal.append(equal_cost[e])
            temp=[]
            for k in range(0,len(len_equal),3):
                temp.append(len_equal[k])
            for k in range(0,len(len_equal),3):
                if len_equal[k]==min(temp):
                    chooosen=len_equal[k+2]+1
        print("\tSELECTED ROUTE (ROUTE "+str(chooosen)+")")
        is_there_a_path=1
    remeaning = data_size - ((i + 1) * packet_size)
    if remeaning>0:
        print("\tPACKET " + str(i+1) + " HAS BEEN SENT")
    if remeaning<0:
        remeaning=0
    print("\tREMAINING DATA SIZE: " +str(float(remeaning))+ " BYTE")
print('******************************')
print('AD-HOC NETWORK SIMULATOR - END')
print('******************************')
command_file.close()
