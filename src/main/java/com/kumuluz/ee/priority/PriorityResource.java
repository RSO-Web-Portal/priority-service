/*
 *  Copyright (c) 2014-2017 Kumuluz and/or its affiliates
 *  and other contributors as indicated by the @author tags and
 *  the contributor list.
 *
 *  Licensed under the MIT License (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  https://opensource.org/licenses/MIT
 *
 *  The software is provided "AS IS", WITHOUT WARRANTY OF ANY KIND, express or
 *  implied, including but not limited to the warranties of merchantability,
 *  fitness for a particular purpose and noninfringement. in no event shall the
 *  authors or copyright holders be liable for any claim, damages or other
 *  liability, whether in an action of contract, tort or otherwise, arising from,
 *  out of or in connection with the software or the use or other dealings in the
 *  software. See the License for the specific language governing permissions and
 *  limitations under the License.
*/
package com.kumuluz.ee.priority;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


import com.kumuluz.ee.logs.cdi.Log;
import com.kumuluz.ee.logs.cdi.LogParams;


@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("priorities")
@Log(LogParams.METRICS)
public class PriorityResource {

    @GET
    public Response getAllPriorities() {
        List<Priority> categories = Database.getPriorities();
        return Response.ok(categories).build();
    }


    @GET
    @Path("{id}")
    public Response getPriority(@PathParam("id") String id) {

        Priority priority = Database.getPriority(id);
        return priority != null
                ? Response.ok(priority).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }


    @POST
    public Response addPriority(Priority priority) {
        Database.addPriority(priority);
        return Response.ok().build();
    }

    @GET
    @Path("compare/{id1}/{id2}")
    public Response comparePriorities(@PathParam("id1") String id1, @PathParam("id2") String id2) {
        int c = Database.comparePriorities(id1, id2);
        if (c == -999) {
            return Response.noContent().build();
        } else {
            return Response.ok(c).build();
        }
    }


    @POST
    @Path("sort")
    public Response sortProducts(String[][] toSort) {

        String[] sorted = new String[toSort.length];

        for(int i=0; i < toSort.length; i++){
            for(int j=1; j < (toSort.length-i); j++){
                if(Database.comparePriorities(toSort[j][1], toSort[j-1][1]) < 0){
                    String[] tmp = toSort[j-1];
                    toSort[j-1] = toSort[j];
                    toSort[j] = tmp;
                }

            }
        }

        for (int i = 0; i < toSort.length; i++) {
            sorted[i] = toSort[i][0];
        }

        return Response.ok(sorted).build();
    }

    @DELETE
    @Path("{id}")
    public Response deletePriority(@PathParam("id") String id) {
        Database.deletePriority(id);
        return Response.ok().build();
    }
}
